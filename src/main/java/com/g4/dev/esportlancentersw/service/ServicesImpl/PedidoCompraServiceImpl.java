package com.g4.dev.esportlancentersw.service.ServicesImpl;

import com.g4.dev.esportlancentersw.exception.NotFoundException;
import com.g4.dev.esportlancentersw.exception.PedidoCompraInvalidComprabanteException;
import com.g4.dev.esportlancentersw.exception.pedidoCompra.PedidoCanceledException;
import com.g4.dev.esportlancentersw.model.PedidoCompra;
import com.g4.dev.esportlancentersw.model.Producto;
import com.g4.dev.esportlancentersw.repository.CategoriaRepository;
import com.g4.dev.esportlancentersw.repository.PedidoCompraRepository;
import com.g4.dev.esportlancentersw.repository.ProductoRepository;
import com.g4.dev.esportlancentersw.service.IServices.IPedidoCompraService;
import com.g4.dev.esportlancentersw.service.IServices.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PedidoCompraServiceImpl implements IPedidoCompraService {

    @Autowired private CategoriaRepository categoriaRepository;

    @Autowired private ProductoRepository productoRepository;

    @Autowired private PedidoCompraRepository pedidoCompraRepository;

    @Autowired private IProductoService iProductoService;


    @Override
    public List<PedidoCompra> listarDatos() {
        return pedidoCompraRepository.findAll()
                .stream()
                .filter(PedidoCompra::getEstado)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PedidoCompra> buscarEntidad(Long id) {
        Optional<PedidoCompra> saleOrderFound = pedidoCompraRepository.findById(id);
        if (saleOrderFound.isEmpty()){
            throw  new NotFoundException("Pedido de compra no encontrada");
        }
        return saleOrderFound;
    }

    @Override
    public Page<PedidoCompra> listarEntidadPorPagina(Pageable p) {
        return pedidoCompraRepository.findAll(p);
    }

    @Override
    public PedidoCompra registrarEntidad(PedidoCompra entidad) {
        validateSaleOrderInput(entidad);
        entidad.setEstado(true);
        pedidoCompraRepository.save(entidad);
        incrementStock(entidad.getIdProducto().getIdProducto(), entidad.getCantidad());
        return entidad;
    }



    @Override
    public PedidoCompra modificarEntidad(PedidoCompra entidad) {
        // TODO: 25/02/2023  Implementar el modificar pedido no impuesto en los sprint
        /**
         * buscarEntidad(entidad.getIdCompra());
         *         entidad.setEstado(true);
         *         pedidoCompraRepository.flush();
         */
        return null;
    }

    @Override
    public boolean eliminarEntidad(Long id) {
        Optional<PedidoCompra> saleOrder = buscarEntidad(id);
        if (!saleOrder.get().getEstado()){
            throw  new NotFoundException("Pedido no encontrado");
        }

        decrementStockByOrderSaleCancelled(saleOrder.get());
        saleOrder.get().setEstado(false);
        pedidoCompraRepository.save(saleOrder.get());
        return true;
    }

    void validateSaleOrderInput(PedidoCompra pd){
        if (!categoriaRepository.existsById(pd.getIdCategoria().getIdCategoria())){
            throw new NotFoundException("Categoria no encontrada");
        }
        if (!productoRepository.existsById(pd.getIdProducto().getIdProducto())){
            throw new NotFoundException("Producto no encontrado");
        }
        if (!pd.getTipoComprabante().equals("F")   ){
            throw  new PedidoCompraInvalidComprabanteException();
        }

    }

    /**
     * Metodo encargado de decrementar el stock de producto del pedido ya registrado cuando este
     * sera cancelado validando que  haya suficiente stock sino lo hay lanza una excepcion
     * @param  ped
     * @return boolean
     * @author Luis DEV
     * @since 1.0
     */
    boolean decrementStockByOrderSaleCancelled( PedidoCompra ped){
        Optional<Producto> productFound = iProductoService.buscarEntidad(ped.getIdProducto().getIdProducto());



        if (productFound.get().getStock() < ped.getCantidad()){
            throw  new PedidoCanceledException();
        }
        productFound.get().setStock(productFound.get().getStock() - ped.getCantidad());
        productoRepository.save(productFound.get());

        return true;
    }

    boolean incrementStock(long id,  int newStock){
        iProductoService.incrementStockByOrderSale(id, newStock);
        return true;

    }
}
