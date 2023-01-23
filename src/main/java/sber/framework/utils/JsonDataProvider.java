package sber.framework.utils;

import sber.framework.models.ConfigData;
import sber.framework.models.TestData;

public class JsonDataProvider {
    private static final String CONFIG_PATH = "./src/main/resources/frameworkConfig.json";
    private static final String TEST_DATA_PATH = "./src/test/resources/testData.json";

    public static final ConfigData configData = JsonUtils.deserializeFile(CONFIG_PATH,ConfigData.class);
    public static final TestData testData = JsonUtils.deserializeFile(TEST_DATA_PATH,TestData.class);
}
