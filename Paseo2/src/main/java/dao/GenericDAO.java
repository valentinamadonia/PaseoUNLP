package dao;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

@Contract public interface GenericDAO <T>{

     List<T> getList();
     T getById(Long id);
	 void agregar(T element);
	 void modificar(T element);
	 void eliminar(T element);
	
}
