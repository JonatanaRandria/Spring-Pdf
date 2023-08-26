package com.example.prog4.service;

import com.example.prog4.config.constant.CompanyConf;

import com.example.prog4.repository.baseRepository.entity.Employee;
import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@AllArgsConstructor
public class PDFUtils {
    CompanyConf CompanyConf;
    public static byte[] convertToPDF(String html) throws DocumentException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        return outputStream.toByteArray();
    }

    public static String parseThymeleafTemplate(Employee employee) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("employee", employee);
        context.setVariable("company",new CompanyConf());

        return templateEngine.process("employee_file", context);
    }
}
