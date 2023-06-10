package test;

import boundary.utilsIO.ImmagineIO;
import database.ImmagineDAO;
import database.ProdottoDAO;
import entity.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImmagineDAOTester {
    public static ArrayList<Immagine> createTestCase(){

        ArrayList<File> imgfiles = new ArrayList<>();
        imgfiles.add(new File("resources/lampada1.jpg"));
        imgfiles.add(new File("resources/lampada2.jpg"));
        imgfiles.add(new File("resources/lampada3.jpg"));
        imgfiles.add(new File("resources/lampada4.jpg"));
        Prodotto prodotto = new Prodotto("lampada","lampada nera con luce calda",imgfiles);
        ProdottoDAO.createProdotto(prodotto);

        return prodotto.getImmagini();
    }

    private void testCreate(ArrayList<Immagine> testCases){
        System.out.println("----Test create----");
        for(Immagine immagine : testCases){
            ImmagineDAO.createImmagine(immagine);
        }
    }

    private void testReadAll(){
        System.out.println("----Test readAll----");
        ArrayList<Immagine> immagini = ImmagineDAO.readAll();
        for(Immagine immagine : immagini){
            System.out.println(immagine);
            ImmagineIO.display(immagine);
        }
    }

    private void testRead(ArrayList<Immagine> testCases){
        System.out.println("----Test read----");
        for(Immagine testCase : testCases){
            Immagine img = ImmagineDAO.readImmagine(testCase.getId());
            System.out.println(img);
            ImmagineIO.display(img);
        }
    }

    private void testUpdate(ArrayList<Immagine> testCases){
        System.out.println("----Test update----");
        for(Immagine testCase : testCases){
            try{
                BufferedImage img = ImageIO.read(new File("resources/armadio1.jpg"));
                testCase.setImage(img);
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
            ImmagineDAO.updateImmagine(testCase);
        }
    }

    private void testDelete(ArrayList<Immagine> testCases){
        System.out.println("----Test delete----");
        for(Immagine testCase : testCases){
            ImmagineDAO.deleteImmagine(testCase.getId());
        }
    }
    public void runTest(){
        ArrayList<Immagine> testCases = createTestCase();
        System.out.println("Test cases:");
        for(Immagine img : testCases){
            System.out.println(img);
            ImmagineIO.display(img);
        }

        testCreate(testCases);
        testRead(testCases);
        testUpdate(testCases);
        testReadAll();
        testDelete(testCases);
        testReadAll();
    }
}