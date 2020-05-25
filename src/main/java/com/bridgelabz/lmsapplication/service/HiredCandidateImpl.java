package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.dto.EmailDto;
import com.bridgelabz.lmsapplication.dto.HiredCandidateDto;
import com.bridgelabz.lmsapplication.exception.UserException;
import com.bridgelabz.lmsapplication.model.HiredCandidateModel;
import com.bridgelabz.lmsapplication.repository.CandidateRepository;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Service
public class HiredCandidateImpl implements IHiredCandidateService {

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private JavaMailSender javaMailSender;


    //METHOD FOR GET HIRED CANDIDATE LIST
    @Override
    public List getHiredCandidatesList() {
        return repository.findAll();
    }

    //METHOD FOR GET HIRED CANDIDATE  PROFILE
    @Override
    public HiredCandidateModel getHiredCandidatesProfile(Long candidateId) {
        return repository.findById(candidateId)
                .orElseThrow(() -> new UserException(UserException.exceptionType.User_Not_FOUND, "Candidate Not Found"));
    }

    //METHOD FOR SEND STATUS EMAIL
    @Override
    public void sendEmail(EmailDto emailDto) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(message, true);
        helper.setSubject(emailDto.getSubject());
        helper.setTo(emailDto.getEmailId());
        helper.setText("Hii Rahul " +
                "You are selected for BridgeLabz fellowship program, if You want to join click on below link (ACCEPT)" +
                " http://localhost:8082/hirecandidate/updatecandidatestatus?id=1&status=Accept " +
                "otherwise (REJECT)" +
                "http://localhost:8082/hirecandidate/updatecandidatestatus?id=1&status=Reject", true);
        javaMailSender.send(message);
    }

    //METHOD FOR UPDATE CANDIDATE STATUS
    @Override
    public HiredCandidateModel updateStatus(Long id, String status) {
        return repository.findById(id)
                .map(hiredCandidateModel -> {
                    hiredCandidateModel.setStatus(status);
                    return hiredCandidateModel;
                }).map(repository::save)
                .orElseThrow(() -> new UserException(UserException.exceptionType.User_Not_FOUND, "Candidate Not Found"));
    }

    //METHOD FOR LOAD HIRED CANDIDATE EXCEL SHEET
    @Override
    public void loadHiredCandidateSheet(String filePath) {
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
                        hiredCandidateDto.setCreatorStamp(cell.getDateCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setCreatorUser(cell.getStringCellValue());
                        HiredCandidateModel hiredCandidate = mapper.map(hiredCandidateDto, HiredCandidateModel.class);
                        repository.save(hiredCandidate);
                    }
                }
                flag = 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
