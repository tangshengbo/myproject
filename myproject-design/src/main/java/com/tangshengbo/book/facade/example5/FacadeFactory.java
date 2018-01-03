package com.tangshengbo.book.facade.example5;

public class FacadeFactory {
	private FacadeFactory(){

	}

	public static FacadeApi createFacadeApi(){
		return new Facade();
	}
}
