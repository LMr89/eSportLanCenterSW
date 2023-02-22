package com.g4.dev.esportlancentersw.util;

public class ValidationMessageConstants {

    //Categoria
    public  final static String CATEGORIA_NOMBRE_NO_BLANCO = "El nombre de la categoria  no debe estar en blanco";


    //Producto
    public  final static String PRODUCTO_NOMBRE_NO_BLANCO = "Nombre de producto faltante";
    public  final static String PROVEDOR_PRODCUCTO_MSG = "Proveedor faltante";
    public  final static String CATEGORIA_PRODUCTO_MSG = "Categoria no debe estar vacio";
    public  final static String PRECIO_UNITARIO_MSG = "No se indico el precio unitario";
    public  final static String STOCK_PRODUCTO_MSG = "No se indico el stock";

    //Proveedor
    public static final  String NOMBRE_PROVEEDOR_MSG = "Nombre del proveedor es obligatorio";
    public static final  String NOMBRE_CONTACTO_PROVEEDOR_MSG = "No se especifico el nombre del contacto";
    public static final  String DIRECCION_PROV_MSG = "La direccion es obligatoria";
    public static final  String TELEFONO_PROV_MSG= "El telefono es obligatorio";



    //Usuario
    public static final  String NOMUSUARIO_USUARIO = "El nombre de usuario es obligatorio";
    public static final  String NOMBRE_USUARIO = "El nombre es obligatorio";
    public static final  String APELLIDO_USUARIO = "El apellido es un campo obligatorio";
    public static final  String DNI_USUARIO = "EL DNI del usuario es requerido";
    public static final  String PASSWORD_USUARIO= "Es obligatorio poner una contraseña valida";

    //Ordenador
    public static final  String NOM_ORDENADOR ="Es obligatorio el nombre del ordenador";
    public static final  String CARACTERISTICAS_ORDENADOR = "Faltan las caracteristicas";
    public static final  String IP_ORDENADOR = "Es obligatorio poner la ip del ordenador";
    public static final  String NUM_ORDENADOR = "Es obligatorio darle un numero de orden al ordenador";


    //Reserva
    public static final  String TIEMPO_RESERVA = "No se indico el tiempo de reserva";
    public static final  String ORDENADOR_RESERVA = "No se indico el ordenador en la reserva";
    public static final  String CLIENTE_RESERVA = "No se indico un cliente ";
    public static final  String USUARIO_RESERVA = "No se especifico el usuario de la reserva";
    public static final  String MONTO_RESERVA = "No se fijo el monto de la reserva";

    //pedido de compra
    public static final  String PRODUCTO_COMPRA = "Es obligatorio especificar los productos";
    public static final  String CANTIDAD_COMPRA = "No se fijo la cantidad comprada";
    public static final  String PRECIO_COMPRA = "No se especifico el precio";
    public static final  String STOCK_COMPRA = "Es obligatorio introducir el stock";
    public static final  String CATEGORIA_COMPRA = "Es obligatorio especificar la categoria";
    public static final  String COMPRABANTE_COMPRA = "Es obligatorio introducir el tipo de comprabante";

    //Comun
    public static final  String CORREO = "El correo es obligatorio";
    public static final  String TELEFONO = "El telefono es de solo 9 digitos";
    public static final  String FECHA = "Es obligatorio poner la fecha";
    public static final  String NOMBRE_CORTO = "El nombre introducido es corto";

}
