package com.example.demo.hadler;

import com.sap.cds.services.handler.annotations.After;
import org.springframework.stereotype.Component;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.ServiceName;

@Component
@ServiceName(CatalogServiceHandler_.CDS_NAME)
public class CatalogServiceHandler implements EventHandler {

	@After(event = CqnService.EVENT_READ)
	public void discountBooks(Stream<Car_> books) {
		books.filter(b -> b.getTitle() != null && b.getStock() != null)
		.filter(b -> b.getStock() > 200)
		.forEach(b -> b.setTitle(b.getTitle() + " (discounted)"));
	}

}