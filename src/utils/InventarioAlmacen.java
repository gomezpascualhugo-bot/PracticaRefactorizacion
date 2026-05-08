package utils;

public class InventarioAlmacen {

    public static final int LIMITE_MESES_ANTIGUEDAD = 12;
    public static final double PORCENTAJE_DESCUENTO_ANTIGUEDAD = 0.15;
    public static final int LIMITES_DIAS_ROTACION = 60;
    public static final double PORCENTAJE_PENALIZACION_ROTACION = 0.10;
    public static final int LIMITE_STOCK_ALTO = 100;
    public static final double PORCENTAJE_BONIFICACION_STOCK = 0.05;
    public static final double PORCENTAJE_AJUSTE_PREMIUM = 0.20;
    public static final double PORCENTAJE_AJUSTE_BASICA = 0.05;

    public double calcularTotalInventario(int numeroProductos, double precioUnitario,
                                          int mesesCatalogo, int diasDesdeUltimaVenta, int stockActual, String tipoCategoria) {
// Cálculo del valor base del inventario
        double valorBase = numeroProductos * precioUnitario;
// Descuento por antigüedad (si lleva más de 12 meses en catálogo)
        double descuentoAntiguedad = calcularDescuentoAntiguedad(mesesCatalogo, valorBase);
// Penalización por baja rotación (más de 60 días sin vender)
        double penalizacionRotacion = calcularPenalizacionRotacion(diasDesdeUltimaVenta, valorBase);
// Bonificación por stock alto (más de 100 unidades)
        double bonificacionStock = calcularBonificacionStock(stockActual, valorBase);
// Ajuste por tipo de categoría
        double ajusteCategoria = calcularAjusteCategoria(tipoCategoria, valorBase);
// Cálculo final
        return valorBase - descuentoAntiguedad - penalizacionRotacion + bonificacionStock + ajusteCategoria;
    }

    public static double calcularAjusteCategoria(String tipoCategoria, double valorBase) {
        double ajusteCategoria = 0;
        if (tipoCategoria.equals("premium")) {
            ajusteCategoria = valorBase * PORCENTAJE_AJUSTE_PREMIUM;//0.20
        } else if (tipoCategoria.equals("basica")) {
            ajusteCategoria = valorBase * PORCENTAJE_AJUSTE_BASICA;//0.05
        }
        return ajusteCategoria;
    }

    public static double calcularBonificacionStock(int stockActual, double valorBase) {
        double bonificacionStock = 0;
        if (stockActual > LIMITE_STOCK_ALTO) {
            bonificacionStock = valorBase * PORCENTAJE_BONIFICACION_STOCK;
        }
        return bonificacionStock;
    }

    public static double calcularPenalizacionRotacion(int diasDesdeUltimaVenta, double valorBase) {
        double penalizacionRotacion = 0;
        if (diasDesdeUltimaVenta > LIMITES_DIAS_ROTACION) {
            penalizacionRotacion = valorBase * PORCENTAJE_PENALIZACION_ROTACION;
        }
        return penalizacionRotacion;
    }

    public static double calcularDescuentoAntiguedad(int mesesCatalogo, double valorBase) {
        double descuentoAntiguedad = 0;
        if (mesesCatalogo > LIMITE_MESES_ANTIGUEDAD) {
            descuentoAntiguedad = valorBase * PORCENTAJE_DESCUENTO_ANTIGUEDAD;
        }
        return descuentoAntiguedad;
    }
}

/*
nP → numeroProductos
 pU → precioUnitario
 mC → mesesCatalogo
 dV → diasDesdeUltimaVenta
 s → stockActual
 tC → tipoCategoria
 vB → valorBase
 descAnt → descuentoAntiguedad
 penalRot → penalizacionRotacion
 bonifStock → bonificacionStock
 ajusteCat → ajusteCategoria

calcularDescuentoAntiguedad
 calcularPenalizacionRotacion
 calcularBonificacionStock
 calcularAjusteCategoria

 LIMITE_MESES_ANTIGUEDAD = 12
 PORCENTAJE_DESCUENTO_ANTIGUEDAD = 0.15
 LIMITE_DIAS_ROTACION = 60
 PORCENTAJE_PENALIZACION_ROTACION = 0.10
 LIMITE_STOCK_ALTO = 100
 PORCENTAJE_BONIFICACION_STOCK = 0.05
 PORCENTAJE_AJUSTE_PREMIUM = 0.20
 PORCENTAJE_AJUSTE_BASICA = -0.05
*/
