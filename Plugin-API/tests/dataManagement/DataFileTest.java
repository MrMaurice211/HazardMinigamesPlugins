package dataManagement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DataFileTest {
    @Test
    void setTest() {
        DataFile dataFile = new DataFile("tests/dataManagement/DataFile");
        dataFile.set("test.path", 1, true);
        Assertions.assertEquals(1, (int) dataFile.get("test.path"));
        dataFile.delete();
    }

    @Test
    void deleteTest() {
        DataFile dataFile = new DataFile("tests/dataManagement/DataFile");
        dataFile.set("test.path", 1);
        dataFile.remove("test.path", true);
        Assertions.assertNull(dataFile.get("test.path"));
        dataFile.delete();
    }
}