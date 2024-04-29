package customer.bookshop.handlers;

import cds.gen.catalogservice.Books;
import cds.gen.catalogservice.CatalogService_;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.ServiceName;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@ServiceName(CatalogService_.CDS_NAME)
public class CatalogServiceHandler implements EventHandler {

    @After(event = CqnService.EVENT_READ)
    public void discountBooks(Stream<Books> books) {
        books.filter(b -> b.getTitle() != null && b.getStock() != null)
                .filter(b -> b.getStock() > 200)
                .forEach(b -> b.setTitle(b.getTitle() + " (discounted)"));
    }

//    @On(entity = Books_.CDS_NAME)
//    public void discountBooks2(CdsReadEventContext context) {
//
//        System.err.println(context.getService().run(Select.from(Books_.class)).batchCount());
//
//        System.err.println(123);
//        context.setResult();
//    }

}