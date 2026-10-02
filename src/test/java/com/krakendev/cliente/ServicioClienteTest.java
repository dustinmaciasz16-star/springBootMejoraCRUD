package com.krakendev.cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.krakendev.cliente.entidades.Cliente;
import com.krakendev.cliente.services.ServicioCliente;

public class ServicioClienteTest {

	@Test
	public void testCrearCliente() {

		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente = new Cliente();

		cliente.setCedula("0951187756");
		cliente.setNombre("Dustin");
		cliente.setApellido("Macias");
		cliente.setEmail("dustin@gmail.com");

		Cliente resultado = servicio.crear(cliente);

		assertNotNull(resultado);
		assertEquals(cliente, resultado);
		assertEquals("dustin@gmail.com", resultado.getEmail());
	}

	@Test
	public void testCrearClienteDuplicado() {

		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente1 = new Cliente();

		cliente1.setCedula("0951187756");
		cliente1.setNombre("Dustin");
		cliente1.setApellido("Macias");
		cliente1.setEmail("dustin@gmail.com");

		Cliente cliente2 = new Cliente();

		cliente2.setCedula("0951187756");
		cliente2.setNombre("Otro");
		cliente2.setApellido("Cliente");
		cliente2.setEmail("otro@gmail.com");

		servicio.crear(cliente1);

		Cliente resultado = servicio.crear(cliente2);

		assertNull(resultado);
	}

	@Test
	public void testBuscarPorCedula() {

		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente = new Cliente();

		cliente.setCedula("0951187756");
		cliente.setNombre("Dustin");
		cliente.setApellido("Macias");
		cliente.setEmail("dustin@gmail.com");

		servicio.crear(cliente);

		Cliente resultado = servicio.buscarPorCedula("0951187756");

		assertNotNull(resultado);
		assertEquals("0951187756", resultado.getCedula());
		assertEquals("dustin@gmail.com", resultado.getEmail());
	}

	@Test
	public void testBuscarPorCedulaNoExiste() {

		ServicioCliente servicio = new ServicioCliente();

		Cliente resultado = servicio.buscarPorCedula("9999999999");

		assertNull(resultado);
	}

	@Test
	public void testListar() {

		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente1 = new Cliente();

		cliente1.setCedula("0951187756");
		cliente1.setNombre("Dustin");
		cliente1.setApellido("Macias");
		cliente1.setEmail("dustin@gmail.com");

		Cliente cliente2 = new Cliente();

		cliente2.setCedula("0951187757");
		cliente2.setNombre("Juan");
		cliente2.setApellido("Perez");
		cliente2.setEmail("juan@gmail.com");

		servicio.crear(cliente1);
		servicio.crear(cliente2);

		List<Cliente> resultado = servicio.listar();

		assertEquals(2, resultado.size());
	}

	@Test
	public void testActualizar() {

		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente = new Cliente();

		cliente.setCedula("0951187756");
		cliente.setNombre("Dustin");
		cliente.setApellido("Macias");
		cliente.setEmail("dustin@gmail.com");

		servicio.crear(cliente);

		Cliente clienteActualizado = new Cliente();

		clienteActualizado.setNombre("David");
		clienteActualizado.setApellido("Zambrano");
		clienteActualizado.setEmail("david@gmail.com");

		Cliente resultado = servicio.actualizar("0951187756", clienteActualizado);

		assertNotNull(resultado);
		assertEquals("David", resultado.getNombre());
		assertEquals("Zambrano", resultado.getApellido());
		assertEquals("david@gmail.com", resultado.getEmail());
	}

	@Test
	public void testActualizarClienteNoExiste() {

		ServicioCliente servicio = new ServicioCliente();

		Cliente clienteActualizado = new Cliente();

		clienteActualizado.setNombre("David");
		clienteActualizado.setApellido("Zambrano");
		clienteActualizado.setEmail("david@gmail.com");

		Cliente resultado = servicio.actualizar("9999999999", clienteActualizado);

		assertNull(resultado);
	}

	@Test
	public void testEliminar() {

		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente = new Cliente();

		cliente.setCedula("0951187756");
		cliente.setNombre("Dustin");
		cliente.setApellido("Macias");
		cliente.setEmail("dustin@gmail.com");

		servicio.crear(cliente);

		boolean resultado = servicio.eliminar("0951187756");

		assertTrue(resultado);
		assertNull(servicio.buscarPorCedula("0951187756"));
	}

	@Test
	public void testEliminarClienteNoExiste() {

		ServicioCliente servicio = new ServicioCliente();

		boolean resultado = servicio.eliminar("9999999999");

		assertFalse(resultado);
	}

}
