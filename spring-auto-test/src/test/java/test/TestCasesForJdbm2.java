package test;

import com.github.yingzhuo.spring.auto.Boot;
import jdbm.PrimaryTreeMap;
import jdbm.RecordManager;
import jdbm.RecordManagerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@SuppressWarnings("all")
public class TestCasesForJdbm2 {

    @Autowired
    private RecordManager manager;

    @Test
    public void test() throws Throwable {
        /** create (or open existing) database */
        String fileName = "helloWorld";
        RecordManager recMan = RecordManagerFactory.createRecordManager(fileName);

        /** Creates TreeMap which stores data in database.
         *  Constructor method takes recordName (something like SQL table name)*/
        String recordName = "firstTreeMap";
        PrimaryTreeMap<Integer,String> treeMap = recMan.treeMap(recordName);

        /** add some stuff to map*/
        treeMap.put(1, "One");
        treeMap.put(2, "Two");
        treeMap.put(3, "Three");

        System.out.println(treeMap.keySet());
        // > [1, 2, 3]

        /** Map changes are not persisted yet, commit them (save to disk) */
        recMan.commit();

        System.out.println(treeMap.keySet());
        // > [1, 2, 3]

        /** Delete one record. Changes are not commited yet, but are visible. */
        treeMap.remove(2);

        System.out.println(treeMap.keySet());
        // > [1, 3]

        /** Did not like change. Roolback to last commit (undo record remove). */
        recMan.rollback();

        /** Key 2 was recovered */
        System.out.println(treeMap.keySet());
        // > [1, 2, 3]

        /** close record manager */
        recMan.close();
    }

}
