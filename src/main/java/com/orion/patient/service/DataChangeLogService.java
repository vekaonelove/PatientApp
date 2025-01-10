package com.orion.patient.service;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.resource.FileSystemResourceAccessor;
import java.io.FileWriter;
import java.io.IOException;

public class DataChangeLogService {
    private static final String DATA_CHANGELOG_PATH = "src/main/resources/db/data-changelog.xml";

    public void addDataChangeSet(String tableName, String columnName, String value) {
        String changeSetId = String.valueOf(System.currentTimeMillis());
        String changeSet = String.format("""
            <changeSet id="%s" author="auto">
                <insert tableName="%s">
                    <column name="%s" value="%s" />
                </insert>
            </changeSet>
        """, changeSetId, tableName, columnName, value);

        try (FileWriter writer = new FileWriter(DATA_CHANGELOG_PATH, true)) {
            writer.write(changeSet);
            writer.write("\n");
        } catch (IOException e) {
            throw new RuntimeException("Error writing to data changelog", e);
        }
    }

    public void applyChangelog() {
        try {
            Database database = DatabaseFactory.getInstance().openDatabase(
                    "jdbc:mysql://localhost:3306/your_database",
                    "username",
                    "password",
                    null,
                    new FileSystemResourceAccessor()
            );

            try (Liquibase liquibase = new Liquibase(DATA_CHANGELOG_PATH, new FileSystemResourceAccessor(), database)) {
                liquibase.update(new Contexts(), new LabelExpression());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error applying changelog", e);
        }
    }
}

