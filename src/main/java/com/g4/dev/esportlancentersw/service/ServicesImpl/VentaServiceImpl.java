package com.g4.dev.esportlancentersw.service.ServicesImpl;

import com.g4.dev.esportlancentersw.exception.common.NotFoundException;
import com.g4.dev.esportlancentersw.exception.producto.ProductoNoEnoughStockException;
import com.g4.dev.esportlancentersw.model.DetalleVenta;
import com.g4.dev.esportlancentersw.model.Venta;
import com.g4.dev.esportlancentersw.repository.ClienteRepository;
import com.g4.dev.esportlancentersw.repository.DetalleVentaRepository;
import com.g4.dev.esportlancentersw.repository.ProductoRepository;
import com.g4.dev.esportlancentersw.repository.VentaRepository;
import com.g4.dev.esportlancentersw.security.repository.UsuarioRepository;
import com.g4.dev.esportlancentersw.service.IServices.IProductoService;
import com.g4.dev.esportlancentersw.service.IServices.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class VentaServiceImpl implements IVentaService {
    @Autowired private VentaRepository ventaRepository;
    @Autowired private DetalleVentaRepository detalleVentaRepository;
    @Autowired private ClienteRepository clienteRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private ProductoRepository productoRepository;

    @Autowired private IProductoService productoService;


    @Override
    public List<Venta> listarDatos() {
        return ventaRepository.findAll()
                .stream()
                .filter(Venta::getEstado)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Venta> buscarEntidad(Long id) {
        Optional<Venta> ventaFound = ventaRepository.findById(id);
        if (ventaFound.isEmpty()){
            throw new NotFoundException("Venta no encontrada");
        }
        return ventaFound;
    }

    @Override
    public Page<Venta> listarEntidadPorPagina(Pageable p) {
        return ventaRepository.findAll(p);
    }

    @Override
    public Venta registrarEntidad(Venta entidad) {
        validateVentaInput(entidad);
        verifyForStockOfEachProduct(entidad);
        Double importe = 0.0;

        for (DetalleVenta detalleVenta : entidad.getDetalleVentas()){
            detalleVenta.setVenta(entidad);
            double subtotal = detalleVenta.getCantidad() *detalleVenta.getPrecio();
            importe += subtotal;

        }

        double igv = importe * 0.18;
        double total = igv + importe;

        entidad.setEstado(true);
        entidad.setFecha(Calendar.getInstance());
        entidad.setIgv(igv);
        entidad.setTotal(total);

        updateStockProduct(entidad);
         ventaRepository.save(entidad);

        return entidad;
    }

    @Override
    public Venta modificarEntidad(Venta entidad) {
        if (buscarEntidad(entidad.getIdVenta()).isPresent()){
            entidad.setEstado(true);
            ventaRepository.save(entidad);
        }
        return entidad;
    }

    @Override
    public boolean eliminarEntidad(Long id) {
        Optional<Venta> ventaFounded = buscarEntidad(id);
        if (ventaFounded.isPresent()){
            ventaFounded.get().setEstado(false);
            ventaRepository.save( ventaFounded.get());
        }
        return true;
    }

    private void validateVentaInput(Venta venta){
        if (!clienteRepository.existsById(venta.getIdCliente().getIdCliente())){
            throw  new NotFoundException("El cliente no existe");
        }
        if (!usuarioRepository.existsById(venta.getIdUsuario().getId())){
            throw  new NotFoundException("El usuario no existe");
        }


        venta.getDetalleVentas()
                .forEach(dt -> {
                    productoRepository.findById(dt.getIdProducto().getIdProducto())
                            .orElseThrow( () -> new NotFoundException("Producto no encontrado" ));
                });
    }

    void verifyForStockOfEachProduct(Venta ve){
        ve.getDetalleVentas()
                .forEach((prod) -> {
                            int stock = productoRepository.getStockByIdProducto(prod.getIdProducto().getIdProducto());

                            if (stock < prod.getCantidad()){
                                throw new ProductoNoEnoughStockException();
                            }
                        }
                );
    }
    boolean updateStockProduct(Venta ven){

        ven.getDetalleVentas()
                        .forEach((prod) -> {
                            productoService.decrementStockForEverySale(prod.getIdProducto().getIdProducto(),
                                    prod.getCantidad());
                        });

        return  true;
    }
}
