package br.com.javaricci;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class PDFGenerator {
    public static void generate(List<Funcionario> funcionarios) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("funcionarios.pdf"));
            document.open();
            document.add(new Paragraph("Lista de Funcionários"));
            for (Funcionario func : funcionarios) {
                document.add(new Paragraph(
                        "ID: " + func.getIdFunc() +
                                ", Nome: " + func.getNomeFunc() +
                                ", Salário: " + func.getSalarioFunc() +
                                ", Horas Base: " + func.getHorasBaseFunc() +
                                ", Salário Hora: " + func.getSalHoraFunc() +
                                ", Salário Dia: " + func.getSalDiaFunc()
                ));
            }
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
