package ch.frankel.so;

import com.hazelcast.collection.IList;
import com.hazelcast.jet.Jet;
import com.hazelcast.jet.JetInstance;
import com.hazelcast.jet.datamodel.Tuple2;
import com.hazelcast.jet.pipeline.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import static com.hazelcast.jet.aggregate.AggregateOperations.toList;

public class LeftJoins {

    public static void main(String[] args) throws InvocationTargetException {

        JetInstance jet = Jet.bootstrappedInstance();

        IList<AddToCart1> addToCartList = jet.getList("cart");
        IList<PageVisit1> paymentList = jet.getList("page");

        // AddToCartData
        AddToCart1 ad1 = new AddToCart1();
        ad1.setNumber(1);
        ad1.setCart("lulu bazar");

        AddToCart1 ad2 = new AddToCart1();
        ad2.setNumber(2);
        ad2.setCart("krishna bazar");

        AddToCart1 ad3 = new AddToCart1();
        ad3.setNumber(3);
        ad3.setCart("ram bazar");

        addToCartList.add(ad1);
        addToCartList.add(ad2);
        addToCartList.add(ad3);

        // Page Data
        PageVisit1 pg1 = new PageVisit1();
        pg1.setNumber(1);
        pg1.setPageName("k login");

        PageVisit1 pg2 = new PageVisit1();
        pg2.setNumber(2);
        pg2.setPageName("plogin");

        paymentList.add(pg1);
        paymentList.add(pg2);

        // creating a piple-line here
        Pipeline p = Pipeline.create();
        BatchStageWithKey<AddToCart1, Object> cart = p.readFrom(Sources.<AddToCart1>list("cart"))
                .groupingKey(cart1 -> cart1.getNumber());
        BatchStageWithKey<PageVisit1, Object> page = p.readFrom(Sources.<PageVisit1>list("page"))
                .groupingKey(page1 -> page1.getNumber());

        BatchStage<Tuple2<List<PageVisit1>, List<AddToCart1>>> joinedLists1 = page.aggregate2(toList(), cart, toList())
                .map(Map.Entry::getValue);

        BatchStage<Tuple2<List<PageVisit1>, List<AddToCart1>>> m = joinedLists1.filter(pair -> !pair.f0().isEmpty());

        m.writeTo(Sinks.logger());

        jet.newJob(p).join();
        // joinedLists.filter(pair -> !pair.isEmpty());
    }
}
