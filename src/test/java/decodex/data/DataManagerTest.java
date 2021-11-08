package decodex.data;

import decodex.data.exception.DataManagerException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataManagerTest {

    private DataManager testManager;
    private Data originalData;
    private Data newData;

    @BeforeEach
    public void createInstances() {
        testManager = new DataManager();
        originalData = new Data("hi");
        newData = new Data("bye");
    }

    @Test
    public void getOriginalData_noData_expectException() {
        assertThrows(DataManagerException.class, () -> testManager.getOriginalData());
    }

    @Test
    public void getCurrentData_noData_expectException() {
        assertThrows(DataManagerException.class, () -> testManager.getCurrentData());
    }

    @Test
    public void getCurrentData_withOriginalData_originalData() throws DataManagerException {
        testManager.setOriginalData(originalData);
        assertArrayEquals(testManager.getCurrentData().getRawBytes(), originalData.getRawBytes());
    }

    @Test
    public void setOriginalData_withOriginalData_originalData() throws DataManagerException {
        testManager.setOriginalData(originalData);
        assertArrayEquals(testManager.getOriginalData().getRawBytes(), originalData.getRawBytes());
    }

    @Test
    public void setCurrentData_noData_expectException() {
        assertThrows(DataManagerException.class, () -> testManager.setCurrentData(newData));
    }

    @Test
    public void setCurrentData_newData_newData() throws DataManagerException {
        testManager.setOriginalData(originalData);
        testManager.setCurrentData(newData);
        assertArrayEquals(testManager.getCurrentData().getRawBytes(), newData.getRawBytes());
    }

    @Test
    public void resetToOriginalData_noUpdates_originalData() throws DataManagerException {
        testManager.setOriginalData(originalData);
        testManager.resetToOriginalData();
        assertArrayEquals(testManager.getCurrentData().getRawBytes(), originalData.getRawBytes());
    }

    @Test
    public void resetToOriginalData_newData_originalData() throws DataManagerException {
        testManager.setOriginalData(originalData);
        testManager.setCurrentData(newData);
        testManager.resetToOriginalData();
        assertArrayEquals(testManager.getCurrentData().getRawBytes(), originalData.getRawBytes());
    }
}
