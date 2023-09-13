package dao;

import org.jvnet.hk2.annotations.Contract;

import modelos.Pedido;

@Contract
public interface IPedidoDAO extends GenericDAO<Pedido> {
	public Pedido getPedido(Long id);
	public void marcarParaRetiro(Pedido element);
	public void marcarParaEntrega(Pedido element);
	public void marcarComoEntregado(Pedido element);
	public void marcarComoRetirado(Pedido element);
	public void confirmar(Pedido element);
	public void activar(Pedido element);
}
