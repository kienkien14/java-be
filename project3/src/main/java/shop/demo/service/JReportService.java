package shop.demo.service;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.demo.entity.User;
import shop.demo.repository.UserRepo;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
@Service
public class JReportService {
    @Autowired
    private UserRepo userRepo;

    public void exportReport(HttpServletResponse response, int id) throws JRException, IOException {
        User user = userRepo.findById(id).orElseThrow(NoResultException::new);

        InputStream reportStream = getClass().getResourceAsStream("/reports.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
        String nameUser = user.getName();
        // Create parameters to pass to the report
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("nameUser", nameUser);
        // Fill the report with data
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JREmptyDataSource());

        // Export the report to PDF and write it to the response output stream
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

}
