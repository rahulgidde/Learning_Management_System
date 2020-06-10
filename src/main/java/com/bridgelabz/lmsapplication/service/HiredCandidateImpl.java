package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.dto.EmailDto;
import com.bridgelabz.lmsapplication.dto.HiredCandidateDto;
import com.bridgelabz.lmsapplication.exception.UserException;
import com.bridgelabz.lmsapplication.model.HiredCandidateModel;
import com.bridgelabz.lmsapplication.repository.CandidateRepository;
import com.bridgelabz.lmsapplication.util.IRabbitMQ;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@Service
public class HiredCandidateImpl implements IHiredCandidateService {

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IRabbitMQ rabbitMQ;

    @Autowired
    private EmailDto emailDto;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * METHOD FOR GET HIRED CANDIDATE LIST
     *
     * @return List
     */
    @Override
    public List getHiredCandidatesList() {
        return repository.findAll();
    }

    /**
     * METHOD FOR GET HIRED CANDIDATE  PROFILE
     *
     * @param candidateId
     * @return HiredCandidateModel
     */
    @Override
    public HiredCandidateModel getHiredCandidatesProfile(Long candidateId) {
        return repository.findById(candidateId)
                .orElseThrow(() -> new UserException(UserException.exceptionType.User_Not_FOUND, "Candidate Not Found"));
    }

    /**
     * METHOD FOR SEND STATUS EMAIL
     *
     * @param emailId
     * @return true
     */
    @Override
    public boolean sendEmail(String emailId) {
        repository.findByEmailId(emailId).map(hiredCandidateModel -> {
            emailDto.setSubject("Update Status");
            emailDto.setEmailId(emailId);
            final Context context = new Context();
            context.setVariable("name", hiredCandidateModel.getFirstName());
            context.setVariable("acceptLink","http://localhost:8082/hirecandidate/updatecandidatestatus?id="
                    +hiredCandidateModel.getId()+"&status=Accept");
            context.setVariable("rejectLink","http://localhost:8082/hirecandidate/updatecandidatestatus?id="
                   +hiredCandidateModel.getId()+"&status=Reject");
            String html = templateEngine.process("jobAcceptance", context);
            emailDto.setBody(html);
            rabbitMQ.sendMessageToQueue(emailDto);
            return hiredCandidateModel;
        })
                .orElseThrow(() -> new UserException(UserException.exceptionType.INVALID_EMAIL_ID, "Invalid EmailId"));
        return true;
    }

    /**
     * METHOD FOR UPDATE CANDIDATE STATUS
     *
     * @param id
     * @param status
     * @return HiredCandidateModel
     */
    @Override
    public HiredCandidateModel updateStatus(Long id, String status) {
        return repository.findById(id)
                .map(hiredCandidateModel -> {
                    hiredCandidateModel.setStatus(status);
                    return hiredCandidateModel;
                }).map(repository::save)
                .orElseThrow(() -> new UserException(UserException.exceptionType.User_Not_FOUND, "Candidate Id Not Found"));
    }

    /**
     * METHOD FOR LOAD HIRED CANDIDATE EXCEL SHEET
     *
     * @param filePath
     * @return true
     */
    @Override
    public boolean loadHiredCandidateSheet(String filePath) {
        int flag = 0;
        HiredCandidateDto hiredCandidateDto = new HiredCandidateDto();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator rows = sheet.rowIterator();
            XSSFCell cell;
            while (rows.hasNext()) {
                XSSFRow row = (XSSFRow) rows.next();
                Iterator cells = row.cellIterator();
                if (flag == 1) {
                    while (cells.hasNext()) {
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setId((long) cell.getNumericCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setFirstName(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setMiddleName(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setLastName(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setEmailId(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setHiredCity(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setDegree(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setHiredDate(cell.getDateCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setMobileNumber((long) cell.getNumericCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setPermanentPincode((long) cell.getNumericCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setHiredLab(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setAttitude(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setCommunicationRemark(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setKnowledgeRemark(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setAggregateRemark(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setStatus(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setCreatorStamp(LocalDateTime.now());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setCreatorUser(hiredCandidateDto.getId());
                        HiredCandidateModel hiredCandidate = mapper.map(hiredCandidateDto, HiredCandidateModel.class);
                        repository.save(hiredCandidate);
                    }
                }
                flag = 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
