package com.cosmicapps.valueline.aws.textract;

import software.amazon.awssdk.services.textract.model.Block;
import software.amazon.awssdk.services.textract.model.BlockType;
import software.amazon.awssdk.services.textract.model.Relationship;
import software.amazon.awssdk.services.textract.model.RelationshipType;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnalyzedDocument {

    private Map<String, Block> data;


    public AnalyzedDocument(List<Block> data) {
        this.data = data.stream()
                    .collect(Collectors.toMap(Block::id, Function.identity()));
    }


    public List<Block> getTables() {

        return data.values().stream()
                .filter(b -> BlockType.TABLE.equals(b.blockType()))
                .collect(Collectors.toList());

    }

    public List<Block> getLines() {

        return data.values().stream()
                .filter(b -> BlockType.LINE.equals(b.blockType()))
                .collect(Collectors.toList());

    }

    List<Block> getTableCells(Block table) {

        List<Relationship> children = table.relationships().stream()
                .filter(r -> RelationshipType.CHILD.equals(r.type()))
                .collect(Collectors.toList());

        //assumption every table has one relationship collection
        return children.get(0).ids()
                .stream()
                .map(id->data.get(id))
                .collect(Collectors.toList());

    }

    List<List<String>> getTableValues(List<Block> cells){

        List<List<String>> table = new LinkedList<>();
        int currRow = 0;
        List<String> currentRow = new LinkedList<>();

        for (Block cell:cells){
            if (cell.rowIndex() != currRow) {
                currentRow = new LinkedList<>();
                table.add(currentRow);
                currRow = cell.rowIndex();
            }
            String cellValue = getCellValue(cell);
            currentRow.add(cellValue);
        }

        return table;

    }

    private String getCellValue(Block cell) {

        Optional<Relationship> child = cell.relationships().stream()
                .filter(r -> RelationshipType.CHILD.equals(r.type()))
                .findFirst();

        if(!child.isPresent()){
            return "";
        }

        return child.get().ids()//list of WORD ids
                .stream()
                .map(id -> data.get(id))
                .map(Block::text)
                .collect(Collectors.joining(" "));
    }


    public  List<List<String>> getTableValues(Block table){
        return getTableValues(getTableCells(table));
    }

    public void printLines(){
        getLines().stream()
                //.filter(b->"nyse-v".equalsIgnoreCase(b.text()))
                .forEach(System.out::println);
    }

    public void printAllBlocks() {

        for (Block block : data.values()) {
            System.out.println(block.toString());
            System.out.println();
        }

        System.out.println("");
        System.out.println("--------------TABLE---------------");
        Block firstTable = getTables().get(0);
        System.out.println(firstTable);
        System.out.println("-------------\\-TABLE-------------");
        System.out.println();

        System.out.println("--------------Table CELLS---------");
        List<Block> tableCells = getTableCells(firstTable);
        for (Block block:tableCells){
            System.out.println(block);
        }
        System.out.println("-------------\\-Table CELLS---------");


        System.out.println("--------------Extracted Rows---------");

        List<List<String>> table = getTableValues(tableCells);

        for (List<String> row:table){
            System.out.println("");
            System.out.print("row : ");
            for (String cell:row){
                System.out.print(cell + " ");
            }
        }
        System.out.println("-------------\\-Extracted Rows---------");


        System.out.println("--------------Specific Cell---------");
        System.out.println(getValue(2,2,table));
        System.out.println("-------------\\-Specific Cell---------");
    }

    private String getValue(int row, int col, List<List<String>> table) {

        List<String> rowLine = table.get(row - 1);
        return rowLine.get(col - 1);


    }


}
