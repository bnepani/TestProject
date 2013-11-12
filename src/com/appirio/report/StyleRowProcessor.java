package com.appirio.report;

import java.util.List;
import java.util.Map;

import net.sf.jxls.parser.Cell;
import net.sf.jxls.processor.RowProcessor;
import net.sf.jxls.transformer.Row;
import net.sf.jxls.transformer.RowCollection;
import net.sf.jxls.transformer.Sheet;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

public class StyleRowProcessor implements RowProcessor {

    String collectionName;

    public StyleRowProcessor(String collectionName) {
        this.collectionName = collectionName;
    }

    public void processRow(Row row, Map namedCells) {
    	short s = '1';
    	List<Cell> cells = row.getCells();
    	for (Cell cell : cells) {
    		org.apache.poi.ss.usermodel.Cell poiCell = cell.getPoiCell();
    		CellStyle cellStyle = poiCell.getCellStyle();
    		cellStyle.setAlignment(s);
		}
    	RowCollection maxNumberOfRowsCollection = row.getMaxNumberOfRowsCollection();
    	RowCollection maxSizeCollection = row.getMaxSizeCollection();
    	int minDependentRowNumber = row.getMinDependentRowNumber();
    	Row parentRow = row.getParentRow();
    	org.apache.poi.ss.usermodel.Row poiRow = row.getPoiRow();
    	RowCollection rowCollectionByCollectionName = row.getRowCollectionByCollectionName("flightLines");
    	List rowCollections = row.getRowCollections();
    	Sheet sheet = row.getSheet();
    	System.out.println("Hello");
        /*// check if processed row has a parent row
        if( row.getParentRow()!=null ){
            // Processed row has parent row. It means we are processing some collection item
            RowCollection rowCollection = row.getParentRow().getRowCollectionByCollectionName( collectionName );
            if( rowCollection.getIterateObject() instanceof FlightLine){
            	FlightLine flightLine = (FlightLine) rowCollection.getIterateObject();
                if( Double.valueOf(flightLine.getAdditionalCost()) >= 2000 ){
                    if( namedCells.containsKey( styleCellLabel ) ){
                        Cell customCell = (Cell) namedCells.get( styleCellLabel );
                        for (int i = 0; i < row.getCells().size(); i++) {
                            Cell cell = (Cell) row.getCells().get(i);
                            HSSFCell hssfCell = cell.getHssfCell();
                            if( hssfCell!=null ){
                                copyStyle( row.getHssfWorkbook(), customCell.getHssfCell(), hssfCell );
                            }
                        }
                    }
                }
            }
        }*/
    }

    private void copyStyle(HSSFWorkbook workbook, HSSFCell fromCell, HSSFCell toCell){
        HSSFCellStyle toStyle = toCell.getCellStyle();
        HSSFCellStyle fromStyle = fromCell.getCellStyle();
        if( fromStyle.getDataFormat() == toStyle.getDataFormat() ){
            toCell.setCellStyle( fromStyle );
        }else{
            HSSFCellStyle newStyle = workbook.createCellStyle();
            newStyle.setAlignment( toStyle.getAlignment() );
            newStyle.setBorderBottom( toStyle.getBorderBottom() );
            newStyle.setBorderLeft( toStyle.getBorderLeft() );
            newStyle.setBorderRight( toStyle.getBorderRight() );
            newStyle.setBorderTop( toStyle.getBorderTop() );
            newStyle.setBottomBorderColor( toStyle.getBottomBorderColor() );
            newStyle.setDataFormat( toStyle.getDataFormat() );
            newStyle.setFillBackgroundColor( fromStyle.getFillBackgroundColor() );
            newStyle.setFillForegroundColor( fromStyle.getFillForegroundColor() );
            newStyle.setFillPattern( fromStyle.getFillPattern() );
            newStyle.setFont( workbook.getFontAt( fromStyle.getFontIndex() ) );
            newStyle.setHidden( toStyle.getHidden() );
            newStyle.setIndention( toStyle.getIndention() );
            newStyle.setLeftBorderColor( toStyle.getLeftBorderColor() );
            newStyle.setLocked( toStyle.getLocked() );
            newStyle.setRightBorderColor( toStyle.getRightBorderColor() );
            newStyle.setTopBorderColor( toStyle.getTopBorderColor() );
            newStyle.setVerticalAlignment( toStyle.getVerticalAlignment() );
            newStyle.setWrapText( toStyle.getWrapText() );
            toCell.setCellStyle( newStyle );
        }
    }
}
