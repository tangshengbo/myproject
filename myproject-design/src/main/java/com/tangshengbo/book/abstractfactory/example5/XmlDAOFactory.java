package com.tangshengbo.book.abstractfactory.example5;

public class XmlDAOFactory extends DAOFactory {
	public OrderDetailDAO createOrderDetailDAO() {
		return new XmlDetailDAOImpl();
	}
	public OrderMainDAO createOrderMainDAO() {
		return new XmlMainDAOImpl();
	}
}