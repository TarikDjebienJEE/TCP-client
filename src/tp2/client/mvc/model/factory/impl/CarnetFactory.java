package tp2.client.mvc.model.factory.impl;

import tp2.client.mvc.model.entity.Carnet;
import tp2.client.mvc.model.factory.ICarnetFactory;

public class CarnetFactory implements ICarnetFactory {

	@Override
	public Carnet createCarnet(String idCarnet) {
		return new Carnet(Integer.valueOf(idCarnet).intValue());
	}

}
