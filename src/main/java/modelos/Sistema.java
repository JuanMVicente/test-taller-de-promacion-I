package modelos;

import excepciones.*;
import modelos.enums.Dia;
import modelos.enums.Estado;
import modelos.enums.ModoOperacion;

import java.util.*;

/**
 * <b>Inv:</b> <br>
 * nombreLocal != null <br>
 * nombreLocal != "" <br>
 * mozos != null <br>
 * mesas != null <br>
 * productos != null <br>
 * operarios != null <br>
 * asignacionMesas != null <br>
 * comandas != null <br>
 * promocionesProducto != null <br>
 * promocionesTemporales != null <br>
 * mozos.size() <= 6 <br>
 * Al menos 2 productos deberán estar promocionados. <br>
 * Se establecen días de promocion para todos los productos. <br>
 */
public class Sistema {

    // TODO: Agregar throws para OperacionNoAutorizadaException

    private String nombreLocal;
    private List<Mozo> mozos;
    private List<Mesa> mesas;
    private List<Producto> productos;
    private List<Operario> operarios;
    private Map<Mozo, List<Mesa>> asignacionMesas;
    private Map<Mesa, Comanda> comandas;
    private Map<Producto, List<PromocionProducto>> promocionesProducto;
    private List<PromocionTemporal> promocionesTemporales;
    private Administrador administrador;
    private ModoOperacion modoOperacion;

    private static Sistema instancia = null;

    private Sistema() {

    }

    /**
     * Devuelve la instancia del sistema.
     * <b>Pre:</b>
     * El sistema ya fue inicializado.
     *
     * @return Instancia del sistema.
     */
    public static Sistema getInstancia() {
        assert isInicializado() : "El sistema no ha sido inicializado";
        return instancia;
    }

    /**
     * Devuelve verdadero si el sistema ya fue inicializado. <br>
     *
     * @return Si el sistema ya fue inicializado.
     */
    public static boolean isInicializado() {
        return instancia != null;
    }

    /**
     * Inicializa el sistema con en nombre indicado y sus colecciones. <br>
     * <b>Pre:</b> <br>
     * nombreLocal != null <br>
     * nombreLocal != "" <br>
     * <b>post:</b> <br>
     * Se crea el sistema con el nombre indicado y las colecciones vacías. <br>
     *
     * @param nombreLocal Nombre del local.
     * @throws SistemaYaInicializadoException si el sistema ya fue inicializado.
     */
    public static void inicializarSistema(String nombreLocal) throws SistemaYaInicializadoException {
        assert nombreLocal != null : "El nombre del local no puede ser nulo";
        assert !nombreLocal.equals("") : "El nombre del local no puede ser vacío";

        if (instancia != null) {
            throw new SistemaYaInicializadoException();
        }

        instancia = new Sistema();

        instancia.nombreLocal = nombreLocal;
        instancia.mozos = new ArrayList<>();
        instancia.mesas = new ArrayList<>();
        instancia.productos = new ArrayList<>();
        instancia.operarios = new ArrayList<>();
        instancia.asignacionMesas = new HashMap<>();
        instancia.comandas = new HashMap<>();
        instancia.promocionesProducto = new HashMap<>();
        instancia.promocionesTemporales = new ArrayList<>();

        try {
            instancia.administrador = Administrador.crearAdministrador();
        } catch (AdministradorExistenteException e) {
            e.printStackTrace();
        }

        instancia.modoOperacion = ModoOperacion.OPERARIO;

        assert instancia.nombreLocal == nombreLocal : "El nombre del local no se asignó correctamente";
        assert instancia.mozos != null : "La lista de mozos no se inicializó correctamente";
        assert instancia.mesas != null : "La lista de mesas no se inicializó correctamente";
        assert instancia.productos != null : "La lista de productos no se inicializó correctamente";
        assert instancia.operarios != null : "La lista de operarios no se inicializó correctamente";
        assert instancia.asignacionMesas != null : "La asignación de mesas no se inicializó correctamente";
        assert instancia.comandas != null : "La lista de comandas no se inicializó correctamente";
        assert instancia.promocionesProducto != null : "La lista de promociones no se inicializó correctamente";
        assert instancia.administrador != null : "El administrador no se inicializó correctamente";
        assert instancia.modoOperacion == ModoOperacion.OPERARIO : "El modo administrador no se inicializó correctamente";

        instancia.verificarInvariantes();
    }

    /**
     * Agrega un mozo al sistema. <br>
     * <b>Pre:</b> <br>
     * mozo != null <br>
     * <b>Post:</b> <br>
     * Se agrega el mozo al sistema. <br>
     *
     * @param mozo El mozo a agregar.
     * @throws MozoExistenteException         Si el mozo a agregar ya existe.
     * @throws MaximaCantidadMozosException   Si se supera la cantidad máxima de mozos.
     * @throws OperacionNoAutorizadaException Si el modo de operación no es administrador.
     */
    public void agregarMozo(Mozo mozo) throws MozoExistenteException, MaximaCantidadMozosException, OperacionNoAutorizadaException {
        assert mozo != null : "El mozo no puede ser nulo";

        if (modoOperacion != ModoOperacion.ADMINISTRADOR)
            throw new OperacionNoAutorizadaException();

        if (mozos.contains(mozo))
            throw new MozoExistenteException(mozo);

        if (mozos.size() == 6)
            throw new MaximaCantidadMozosException(mozos.size());

        mozos.add(mozo);

        assert mozos.contains(mozo) : "El mozo no se agregó";
        verificarInvariantes();
    }

    /**
     * Elimina un mozo del sistema. <br>
     * <b>Pre:</b> <br>
     * mozo != null <br>
     * <b>Post:</b> <br>
     * Se elimina el mozo del sistema. <br>
     * @param mozo El mozo a eliminar.
     * @throws MozoInexistenteException Si el mozo a eliminar no existe.
     * @throws OperacionNoAutorizadaException Si el usuario no tiene permisos para realizar la operación.
     */
    public void eliminarMozo(Mozo mozo) throws MozoInexistenteException, OperacionNoAutorizadaException {
        assert mozo != null : "El mozo no puede ser nulo";

        if (modoOperacion != ModoOperacion.ADMINISTRADOR)
            throw new OperacionNoAutorizadaException();

        if (!mozos.contains(mozo))
            throw new MozoInexistenteException(mozo);

        mozos.remove(mozo);

        assert !mozos.contains(mozo) : "El mozo no se eliminó";
        verificarInvariantes();
    }

    /**
     * Agrega un producto al sistema. <br>
     * <b>Pre:</b> <br>
     * producto != null <br>
     * <b>Post:</b> <br>
     * Se agrega el producto al sistema. <br>
     *
     * @param producto El producto a agregar.
     * @throws ProductoExistenteException     Si el producto ya existe.
     * @throws OperacionNoAutorizadaException Si el sistema no está en modo administrador.
     */
    public void agregarProducto(Producto producto) throws ProductoExistenteException, OperacionNoAutorizadaException {
        assert producto != null : "El producto no puede ser nulo";

        if (modoOperacion != ModoOperacion.ADMINISTRADOR)
            throw new OperacionNoAutorizadaException();

        if (productos.contains(producto))
            throw new ProductoExistenteException(producto);

        productos.add(producto);

        assert productos.contains(producto) : "El producto no se agregó";
        verificarInvariantes();
    }

    /**
     * Elimina un producto del sistema. <br>
     * <b>Pre:</b> <br>
     * producto != null <br>
     * <b>Post:</b> <br>
     * Se elimina el producto del sistema. <br>
     *
     * @param producto El producto a eliminar.
     * @throws ProductoInexistenteException   Si el producto no existe.
     * @throws ProductoEnComandaException     Si el producto está asociado a una comanda.
     * @throws OperacionNoAutorizadaException Si el sistema no está en modo administrador.
     */
    public void eliminarProducto(Producto producto) throws ProductoInexistenteException, ProductoEnComandaException, OperacionNoAutorizadaException {
        assert producto != null : "El producto no puede ser nulo";
        assert productos.contains(producto) : "El producto no se encuentra en el sistema";

        if (modoOperacion != ModoOperacion.ADMINISTRADOR)
            throw new OperacionNoAutorizadaException();

        // TODO: Verificar que el producto no esté en una comanda.

        if (!productos.contains(producto)) {
            throw new ProductoInexistenteException(producto);
        }

        // TODO: Verifica que el producto no esté asociado a una comanda.

        productos.remove(producto);

        assert !productos.contains(producto) : "El producto no se eliminó";
        verificarInvariantes();
    }

    /**
     * Establece el estado de un mozo. <br>
     * <b>Pre:</b> <br>
     * mozo != null <br>
     * estado != null <br>
     * <b>Post:</b> <br>
     * Se establece el estado del mozo. <br>
     *
     * @param mozo   El mozo cuyo estado se desea cambiar.
     * @param estado El nuevo estado del mozo.
     * @throws MozoInexistenteException Si el mozo no existe.
     */
    public void establecerEstadoMozo(Mozo mozo, Estado estado) throws MozoInexistenteException {
        assert mozo != null : "El mozo no puede ser nulo";
        assert estado != null : "El estado no puede ser nulo";

        if (!mozos.contains(mozo))
            throw new MozoInexistenteException(mozo);

        mozo.setEstado(estado);

        assert mozo.getEstado() == estado : "El estado no se estableció";
        verificarInvariantes();
    }

    /**
     * Agrega una mesa al sistema. <br>
     * <b>Pre:</b> <br>
     * mesa != null <br>
     * <b>Post:</b> <br>
     * Se agrega la mesa al sistema. <br>
     * @param mesa La mesa a agregar.
     * @throws MesaRepetidaException si la mesa ya está en el sistema
     * @throws OperacionNoAutorizadaException si el sistema no está en modo administrador
     */
    public void agregarMesa(Mesa mesa) throws MesaRepetidaException, OperacionNoAutorizadaException {
        assert mesa != null : "La mesa no puede ser nula";

        if (modoOperacion != ModoOperacion.ADMINISTRADOR)
            throw new OperacionNoAutorizadaException();

        if (mesas.stream().anyMatch(m -> m.getNroMesa() == mesa.getNroMesa())) {
            throw new MesaRepetidaException();
        }

        mesas.add(mesa);

        assert mesas.contains(mesa) : "La mesa no se agregó";
        verificarInvariantes();
    }

    /**
     * Elimina una mesa del sistema. <br>
     * <b>Pre:</b> <br>
     * mesa != null <br>
     * La mesa debe estar en el sistema. <br>
     * <b>Post:</b> <br>
     * Se elimina la mesa del sistema. <br>
     * @param mesa La mesa a eliminar.
     * @throws MesaInexistenteException Si la mesa no existe.
     * @throws OperacionNoAutorizadaException Si el sistema no está en modo administrador.
     */
    public void eliminarMesa(Mesa mesa) throws MesaInexistenteException, OperacionNoAutorizadaException {
        assert mesa != null : "La mesa no puede ser nula";

        if (modoOperacion != ModoOperacion.ADMINISTRADOR)
            throw new OperacionNoAutorizadaException();

        if (!mesas.contains(mesa))
            throw new MesaInexistenteException(mesa);

        mesas.remove(mesa);

        assert !mesas.contains(mesa) : "La mesa no se eliminó";
        verificarInvariantes();
    }

    /**
     * Asigna una mesa a un mozo. <br>
     * <b>Pre:</b> <br>
     * mozo != null <br>
     * mesa != null <br>
     * <b>Post:</b>  <br>
     * Se asigna la mesa al mozo. <br>
     *
     * @param mozo El mozo asignado para la mesa.
     * @param mesa La mesa a asignar.
     * @throws MozoInexistenteException Si el mozo no existe.
     * @throws MesaInexistenteException Si la mesa no existe.
     */
    public void asignarMesa(Mozo mozo, Mesa mesa) throws MozoInexistenteException, MesaInexistenteException {
        assert mozo != null : "El mozo no puede ser nulo";
        assert mesa != null : "La mesa no puede ser nula";

        if (!mozos.contains(mozo))
            throw new MozoInexistenteException(mozo);

        if (!mesas.contains(mesas))
            throw new MesaInexistenteException(mesa);

        asignacionMesas.computeIfAbsent(mozo, k -> new ArrayList<>()).add(mesa);

        assert asignacionMesas.get(mozo).contains(mesa) : "La mesa no se asignó al mozo";
        verificarInvariantes();
    }

    /**
     * Crea una comanda para una mesa.
     * <b>Pre:</b> <br>
     * mesa != null <br>
     * <b>Post:</b> <br>
     * Se crea una comanda para la mesa. <br>
     *
     * @param mesa La mesa para la cual se crea la comanda.
     * @throws MesaInexistenteException Si la mesa no existe.
     * @throws MesaOcupadaException     Si la mesa ya tiene una comanda.
     */
    public void crearComanda(Mesa mesa) throws MesaInexistenteException, MesaOcupadaException {
        assert mesa != null : "La mesa no puede ser nula";

        if (!mesas.contains(mesa))
            throw new MesaInexistenteException(mesa);

        if (mesa.estaOcupada()) {
            throw new MesaOcupadaException(mesa);
        }

        comandas.put(mesa, new Comanda());
        mesa.ocupar();

        assert comandas.containsKey(mesa) : "La comanda no se creó";
        verificarInvariantes();
    }

    /**
     * Cierra una comanda para una mesa. <br>
     * <b>Pre:</b> <br>
     * mesa != null <br>
     * <b>Post:</b> <br>
     * Se cierra la comanda para la mesa. <br>
     *
     * @param mesa La mesa de la comanda a cerrar.
     * @throws MesaInexistenteException Si la mesa no existe.
     * @throws MesaNoOcupadaException   Si la mesa no está ocupada.
     */
    public void cerrarComanda(Mesa mesa) throws MesaInexistenteException, MesaNoOcupadaException {
        assert mesa != null : "La mesa no puede ser nula";

        if (!mesas.contains(mesa))
            throw new MesaInexistenteException(mesa);

        if (!mesa.estaOcupada()) {
            throw new MesaNoOcupadaException(mesa);
        }

        Comanda comanda = comandas.get(mesa);
        Mozo mozo = null;
        boolean encontrado = false;
        Iterator<Map.Entry<Mozo, List<Mesa>>> it = asignacionMesas.entrySet().iterator();
        while (it.hasNext() && !encontrado) {
            Map.Entry<Mozo, List<Mesa>> entry = it.next();
            if (entry.getValue().contains(mesa)) {
                mozo = entry.getKey();
                encontrado = true;
            }
        }

        comandas.remove(mesa);
        mesa.desocupar();

        // TODO: Hacer facturación con la comanda.

        assert !comandas.containsKey(mesa) : "La comanda no se cerró";
        verificarInvariantes();
    }

    /**
     * Agrega un pedido a una comanda. <br>
     * <b>Pre:</b> <br>
     * mesa != null <br>
     * pedido != null <br>
     * <b>Post:</b> <br>
     * Se agrega el pedido a la comanda de la mesa. <br>
     * Se descuenta el stock del producto. <br>
     * @param mesa   Mesa para agregar el pedido.
     * @param pedido Pedido a agregar.
     * @throws ComandaInexistenteException Si la mesa no tiene una comanda.
     * @throws StockInsuficienteException  Si no hay stock suficiente para el pedido.
     */
    public void agregarPedido(Mesa mesa, Pedido pedido) throws ComandaInexistenteException, StockInsuficienteException {
        // TODO: Implementar
    }

    /**
     * Agrega un operario al sistema. <br>
     * <b>Pre:</b> <br>
     * operario != null <br>
     * <b>Post:</b> <br>
     * Se agrega el operario al sistema. <br>
     * @param operario Operario a agregar.
     * @throws OperarioExistenteException     Si el operario no existe.
     * @throws OperacionNoAutorizadaException Si el sistema no está en modo administrador.
     */
    public void agregarOperario(Operario operario) throws OperarioExistenteException, OperacionNoAutorizadaException {
        assert operario != null : "El operario no puede ser nulo";

        if (modoOperacion != ModoOperacion.ADMINISTRADOR)
            throw new OperacionNoAutorizadaException();

        if (operarios.contains(operario)) {
            throw new OperarioExistenteException(operario);
        }

        operarios.add(operario);

        assert operarios.contains(operario) : "El operario no se agregó";
        verificarInvariantes();
    }

    /**
     * Elimina un operario del sistema. <br>
     * <b>Pre:</b> <br>
     * operario != null <br>
     * <b>Post:</b> <br>
     * Se elimina el operario del sistema. <br>
     *
     * @param operario El operario a eliminar.
     * @throws OperarioInexistenteException   si el operario no está en el sistema.
     * @throws OperacionNoAutorizadaException Si el sistema no está en modo administrador.
     */
    public void eliminarOperario(Operario operario) throws OperarioInexistenteException, OperacionNoAutorizadaException {
        assert operario != null : "El operario no puede ser nulo";

        if (modoOperacion != ModoOperacion.ADMINISTRADOR)
            throw new OperacionNoAutorizadaException();

        if (!operarios.contains(operario)) {
            throw new OperarioInexistenteException(operario.getNombreUsuario());
        }

        operarios.remove(operario);

        assert !operarios.contains(operario) : "El operario no se eliminó";
        verificarInvariantes();
    }

    public ModoOperacion getModoOperacion() {
        return modoOperacion;
    }

    /**
     * Asigna el modo de operacion del sistema.
     * <b>Pre:</b> modoOperacion != null
     * <b>Post: </b> Se asigna el modo de operacion del sistema.
     *
     * @param modoOperacion El modo de operacion del sistema.
     */
    public void setModoOperacion(ModoOperacion modoOperacion) {
        assert modoOperacion != null : "El modo de operacion no puede ser nulo";

        this.modoOperacion = modoOperacion;

        assert this.modoOperacion == modoOperacion : "El modo de operacion no se establecio";
        verificarInvariantes();
    }


    /**
     * Busca un operario por su nombre de usuario.
     *
     * @param nombreUsuario nombre de usuario del operario.
     * @return el operario con el nombre de usuario indicado.
     * @throws OperarioInexistenteException si no existe un operario con el nombre de usuario indicado.
     */
    public Operario buscarOperario(String nombreUsuario) throws OperarioInexistenteException {
        for (Operario operario : operarios) {
            if (operario.getNombreUsuario().equals(nombreUsuario)) {
                return operario;
            }
        }

        if (administrador.getNombreUsuario().equals(nombreUsuario)) {
            return administrador;
        } else {
            throw new OperarioInexistenteException(nombreUsuario);
        }
    }

    /**
     * Agrega una promoción por producto al sistema. <br>
     * <b>Pre:</b> <br>
     * producto != null <br>
     * promocion != null <br>
     * <b>Post:</b> <br>
     * Se agrega la promoción al sistema. <br>
     *
     * @param producto  Producto para promocionar.
     * @param promocion Promocion a aplicar.
     * @throws ProductoInexistenteException Si el producto no existe.
     */
    public void agregarPromocionProducto(Producto producto, PromocionProducto promocion) throws ProductoInexistenteException {
        // TODO: Implementar
    }

    /**
     * Agrega una promoción temporal al sistema. <br>
     * <b>Pre:</b> <br>
     * promocion != null <br>
     * <b>Post:</b> <br>
     * Se agrega la promoción al sistema. <br>
     *
     * @param promocion Promocion a aplicar.
     */
    public void agregarPromocionTemporal(PromocionTemporal promocion) {
        // TODO: Implementar
    }

    /**
     * Elimina una promoción por producto del sistema. <br>
     * <b>Pre:</b> <br>
     * producto != null <br>
     * <b>Post:</b> <br>
     * Se elimina la promoción del sistema. <br>
     *
     * @param producto Producto para eliminar promocion.
     * @throws ProductoInexistenteException Si el producto no existe.
     */
    public void eliminarPromocionProducto(Producto producto) throws ProductoInexistenteException {
        // TODO: Implementar
    }

    /**
     * Elimina una promoción temporal del sistema. <br>
     * <b>Pre:</b> <br>
     * promocion != null <br>
     * <b>Post:</b> <br>
     * Se elimina la promoción del sistema. <br>
     *
     * @param promocion Promocion a eliminar.
     * @throws PromocionInexistenteException Si la promoción no existe.
     */
    public void eliminarPromocionTemporal(PromocionTemporal promocion) throws PromocionInexistenteException {
        // TODO: Implementar
    }

    // TODO Estadisticas mensuales empleado.

    private void verificarInvariantes() {
        assert nombreLocal != null : "El nombre del local no puede ser nulo";
        assert !nombreLocal.equals("") : "El nombre del local no puede ser vacío";
        assert mozos != null : "La lista de mozos no puede ser nula";
        assert mesas != null : "La lista de mesas no puede ser nula";
        assert productos != null : "La lista de productos no puede ser nula";
        assert operarios != null : "La lista de operarios no puede ser nula";
        assert asignacionMesas != null : "La asignación de mesas no puede ser nula";
        assert comandas != null : "La lista de comandas no puede ser nula";
        assert promocionesProducto != null : "La lista de promociones no puede ser nula";
        assert mozos.size() <= 6 : "No puede haber más de 6 mozos";
        assert promocionesProducto.values().stream().allMatch(promocionesProducto -> promocionesProducto != null
                && promocionesProducto.stream().allMatch(promocion -> promocion.getDiasPromo() != null)) : "Debe haber días de promoción para todos los productos";

        Dia diaActual = Dia.getDiaActual();
        Set<Map.Entry<Producto, List<PromocionProducto>>> entries = promocionesProducto.entrySet();
        Iterator<Map.Entry<Producto, List<PromocionProducto>>> iterator = entries.iterator();
        int productosPromocionadosHoy = 0;
        while (iterator.hasNext() && productosPromocionadosHoy < 2) {
            Map.Entry<Producto, List<PromocionProducto>> entry = iterator.next();
            List<PromocionProducto> promociones = entry.getValue();
            Iterator<PromocionProducto> iteratorPromociones = promociones.iterator();
            boolean productoPromocionadoHoy = false;
            while (iteratorPromociones.hasNext() && productosPromocionadosHoy < 2 && !productoPromocionadoHoy) {
                Promocion promocion = iteratorPromociones.next();
                if (promocion.getDiasPromo().contains(diaActual)) {
                    productosPromocionadosHoy++;
                    productoPromocionadoHoy = true;
                }
            }
        }

        assert productosPromocionadosHoy >= 2 : "Debe haber al menos 2 productos promocionados hoy";
    }
}
