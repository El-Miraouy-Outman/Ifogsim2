package org.printInExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.variable.Variable;

public class CreationFichierExcel {
    public static void writeInExcel( long EXECUTION_TIME,
                                     Double ACTION_COMMAND,
                                     Double RAW_DATA,
                                     Double PROCESSED_DATA,
                                     Double M_SENSOR,
                                     double total_Energy,
                                     double  Cost_Of_Execution,
                                     double   network_usage,
                                     double  migrationTime) {
        try {
            System.out.println("Début =====");
            File file = new File("/home/d.aitomar/IdeaProjects/Ifogsim_Kmeans_SOM-main/src/org/fog/test/perfeval/resultats_SOM_KMEANS.xls");
            FileInputStream fis = null;
            FileOutputStream out = null;
            Workbook wb = null;

            if (file.exists()) {
                fis = new FileInputStream(file);
                wb = new HSSFWorkbook(fis);
            }
            else {
                wb = new HSSFWorkbook();
                // Créer une nouvelle feuille dans le classeur
                wb.createSheet("KMEANS");
                Sheet mySheet = wb.getSheet("KMEANS");
                Row myRow=null;
                myRow = mySheet.createRow(0);
                myRow.createCell(0).setCellValue("Number Of USER");
                myRow.createCell(1).setCellValue("EXECUTION TIME");
                myRow.createCell(2).setCellValue("ACTION_COMMAND");
                myRow.createCell(3).setCellValue("RAW_DATA");
                myRow.createCell(4).setCellValue("PROCESSED_DATA");
                myRow.createCell(5).setCellValue("M-SENSOR");
                myRow.createCell(6).setCellValue("Cost Of Execution In Cloud");
                myRow.createCell(7).setCellValue("Total network usage ");
                myRow.createCell(8).setCellValue("Total time migration");
                myRow.createCell(9).setCellValue("Total Energy");
            }
            System.out.println("numbre of shet :"+ wb.getNumberOfSheets());
            Sheet mySheet1 = wb.getSheet("KMEANS");
            Row myRow=null;

            // Créer une nouvelle ligne à l'index 2 dans la feuille Excel
            int lastRowNum = mySheet1.getLastRowNum();
            myRow = mySheet1.createRow(lastRowNum+1);
            
            myRow.createCell(0).setCellValue(String.valueOf(Variable.numberUser));
            myRow.createCell(1).setCellValue(String.valueOf(EXECUTION_TIME));
            myRow.createCell(2).setCellValue(String.valueOf(ACTION_COMMAND));
            myRow.createCell(3).setCellValue(String.valueOf(RAW_DATA));
            myRow.createCell(4).setCellValue(String.valueOf(PROCESSED_DATA));
            myRow.createCell(5).setCellValue(String.valueOf(M_SENSOR));
            myRow.createCell(6).setCellValue(String.valueOf(Cost_Of_Execution));
            myRow.createCell(7).setCellValue(String.valueOf(network_usage));
            myRow.createCell(8).setCellValue(String.valueOf(migrationTime));
            myRow.createCell(9).setCellValue(String.valueOf(total_Energy));


            out = new FileOutputStream(file);
            wb.write(out);

            System.out.println("Valeurs enregistrez sur  EXCEL.");

            if (fis != null) {
                fis.close();
            }

            if (out != null) {
                out.close();
            }

        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);

        } catch (Exception e) {
            System.out.println("Exception lors de la création du fichier Excel : " + e);
        }
    }
}

