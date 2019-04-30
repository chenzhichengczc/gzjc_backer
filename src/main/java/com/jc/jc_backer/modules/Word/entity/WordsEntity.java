package com.jc.jc_backer.modules.Word.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class WordsEntity {
    /**
     * 文档编号
     */
    @Value("${word.DocId}")
    private String docId;

    /**
     * 版本号
     */
    @Value("${word.Version}")
    private String version;

    /**
     * 日期
     */
    @Value("${word.Date}")
    private String date;

    /**
     * 修订记录
     */
    @Value("${word.Version1")
    private String version1;
    @Value("${word.Date1}")
    private String date1;
    @Value("${word.Enact1}")
    private String enact1;
    @Value("${word.Sort1}")
    private String sort1;
    @Value("${word.Ratify1}")
    private String ratify1;
    @Value("${word.Describe1}")
    private String describe1;

    @Value("${word.Version2}")
    private String version2;
    @Value("${word.Date2}")
    private String date2;
    @Value("${word.Enact2}")
    private String enact2;
    @Value("${word.Sort2}")
    private String sort2;
    @Value("${word.Ratify2}")
    private String ratify2;
    @Value("${word.Describe2}")
    private String describe2;

    @Value("${word.Version3}")
    private String version3;
    @Value("${word.Date3}")
    private String date3;
    @Value("${word.Enact3}")
    private String enact3;
    @Value("${word.Sort3}")
    private String sort3;
    @Value("${word.Ratify3}")
    private String ratify3;
    @Value("${word.Describe3}")
    private String describe3;

    /**
     * 术语与缩写解释
     */
    @Value("${word.Term1}")
    private String term1;
    @Value("${word.Explanation1}")
    private String explanation1;

    @Value("${word.Term2}")
    private String term2;
    @Value("${word.Explanation2}")
    private String explanation2;

    /**
     * 参考文档
     */
    @Value("${word.DocName1}")
    private String docName1;
    @Value("${word.DocLabelNo1}")
    private String docLabelNo1;

    @Value("${word.DocName2}")
    private String docName2;
    @Value("${word.DocLabelNo2}")
    private String docLabelNo2;

    /**
     * 用户需求1
     */
    @Value("${word.ReqID}")
    private String reqId;
    @Value("${word.Priority}")
    private String priority;

    /**
     * 用例1
     */
    @Value("${word.TestCaseName1}")
    private String testCaseName1;
    @Value("${word.LeadRole1}")
    private String leadRole1;
    @Value("${word.Precondition1}")
    private String precondition1;
    @Value("${word.PostCondition1}")
    private String postCondition1;
    @Value("${word.FunctionalCategory1}")
    private String functionalCategory1;
    @Value("${word.FunctionalDesignDescription1}")
    private String functionalDesignDescription1;
    @Value("${word.SupplementaryInstruction1}")
    private String supplementaryInstruction1;
    @Value("${word.ExpectedToArtificial1}")
    private String expectedToArtificial1;

    @Value("${word.TestCaseName2}")
    private String testCaseName2;
    @Value("${word.LeadRole2}")
    private String leadRole2;
    @Value("${word.Precondition2}")
    private String precondition2;
    @Value("${word.PostCondition2}")
    private String postCondition2;
    @Value("${word.FunctionalCategory2}")
    private String functionalCategory2;
    @Value("${word.FunctionalDesignDescription2}")
    private String functionalDesignDescription2;
    @Value("${word.SupplementaryInstruction2}")
    private String supplementaryInstruction2;
    @Value("${word.ExpectedToArtificial2}")
    private String expectedToArtificial2;

    @Value("${word.TestCaseName3}")
    private String testCaseName3;
    @Value("${word.LeadRole3}")
    private String leadRole3;
    @Value("${word.Precondition3}")
    private String precondition3;
    @Value("${word.PostCondition3}")
    private String postCondition3;
    @Value("${word.FunctionalCategory3}")
    private String functionalCategory3;
    @Value("${word.FunctionalDesignDescription3}")
    private String functionalDesignDescription3;
    @Value("${word.SupplementaryInstruction3}")
    private String supplementaryInstruction3;
    @Value("${word.ExpectedToArtificial3}")
    private String expectedToArtificial3;

    @Value("${word.TestCaseName4}")
    private String testCaseName4;
    @Value("${word.LeadRole4}")
    private String leadRole4;
    @Value("${word.Precondition4}")
    private String precondition4;
    @Value("${word.PostCondition4}")
    private String postCondition4;
    @Value("${word.FunctionalCategory4}")
    private String functionalCategory4;
    @Value("${word.FunctionalDesignDescription4}")
    private String functionalDesignDescription4;
    @Value("${word.SupplementaryInstruction4}")
    private String supplementaryInstruction4;
    @Value("${word.ExpectedToArtificial4}")
    private String expectedToArtificial4;

    @Value("${word.TestCaseName5}")
    private String testCaseName5;
    @Value("${word.LeadRole5}")
    private String leadRole5;
    @Value("${word.Precondition5}")
    private String precondition5;
    @Value("${word.PostCondition5}")
    private String postCondition5;
    @Value("${word.FunctionalCategory5}")
    private String functionalCategory5;
    @Value("${word.FunctionalDesignDescription5}")
    private String functionalDesignDescription5;
    @Value("${word.SupplementaryInstruction5}")
    private String supplementaryInstruction5;
    @Value("${word.ExpectedToArtificial5}")
    private String expectedToArtificial5;

    @Value("${word.TestCaseName6}")
    private String testCaseName6;
    @Value("${word.LeadRole6}")
    private String leadRole6;
    @Value("${word.Precondition6}")
    private String precondition6;
    @Value("${word.PostCondition6}")
    private String postCondition6;
    @Value("${word.FunctionalCategory6}")
    private String functionalCategory6;
    @Value("${word.FunctionalDesignDescription6}")
    private String functionalDesignDescription6;
    @Value("${word.SupplementaryInstruction6}")
    private String supplementaryInstruction6;
    @Value("${word.ExpectedToArtificial6}")
    private String expectedToArtificial6;

    @Value("${word.TestCaseName7}")
    private String testCaseName7;
    @Value("${word.LeadRole7}")
    private String leadRole7;
    @Value("${word.Precondition7}")
    private String precondition7;
    @Value("${word.PostCondition7}")
    private String postCondition7;
    @Value("${word.FunctionalCategory7}")
    private String functionalCategory7;
    @Value("${word.FunctionalDesignDescription7}")
    private String functionalDesignDescription7;
    @Value("${word.SupplementaryInstruction7}")
    private String supplementaryInstruction7;
    @Value("${word.ExpectedToArtificial7}")
    private String expectedToArtificial7;

    @Value("${word.TestCaseName8}")
    private String testCaseName8;
    @Value("${word.LeadRole8}")
    private String leadRole8;
    @Value("${word.Precondition8}")
    private String precondition8;
    @Value("${word.PostCondition8}")
    private String postCondition8;
    @Value("${word.FunctionalCategory8}")
    private String functionalCategory8;
    @Value("${word.FunctionalDesignDescription8}")
    private String functionalDesignDescription8;
    @Value("${word.SupplementaryInstruction8}")
    private String supplementaryInstruction8;
    @Value("${word.ExpectedToArtificial8}")
    private String expectedToArtificial8;

    @Value("${word.TestCaseName9}")
    private String testCaseName9;
    @Value("${word.LeadRole9}")
    private String leadRole9;
    @Value("${word.Precondition9}")
    private String precondition9;
    @Value("${word.PostCondition9}")
    private String postCondition9;
    @Value("${word.FunctionalCategory9}")
    private String functionalCategory9;
    @Value("${word.FunctionalDesignDescription9}")
    private String functionalDesignDescription9;
    @Value("${word.SupplementaryInstruction9}")
    private String supplementaryInstruction9;
    @Value("${word.ExpectedToArtificial9}")
    private String expectedToArtificial9;

    @Value("${word.TestCaseName10}")
    private String testCaseName10;
    @Value("${word.LeadRole10}")
    private String leadRole10;
    @Value("${word.Precondition10}")
    private String precondition10;
    @Value("${word.PostCondition10}")
    private String postCondition10;
    @Value("${word.FunctionalCategory10}")
    private String functionalCategory10;
    @Value("${word.FunctionalDesignDescription10}")
    private String functionalDesignDescription10;
    @Value("${word.SupplementaryInstruction10}")
    private String supplementaryInstruction10;
    @Value("${word.ExpectedToArtificial10}")
    private String expectedToArtificial10;

    @Value("${word.TestCaseName11}")
    private String testCaseName11;
    @Value("${word.LeadRole11}")
    private String leadRole11;
    @Value("${word.Precondition11}")
    private String precondition11;
    @Value("${word.PostCondition11}")
    private String postCondition11;
    @Value("${word.FunctionalCategory11}")
    private String functionalCategory11;
    @Value("${word.FunctionalDesignDescription11}")
    private String functionalDesignDescription11;
    @Value("${word.SupplementaryInstruction11}")
    private String supplementaryInstruction11;
    @Value("${word.ExpectedToArtificial11}")
    private String expectedToArtificial11;

    @Value("${word.TestCaseName12}")
    private String testCaseName12;
    @Value("${word.LeadRole12}")
    private String leadRole12;
    @Value("${word.Precondition12}")
    private String precondition12;
    @Value("${word.PostCondition12}")
    private String postCondition12;
    @Value("${word.FunctionalCategory12}")
    private String functionalCategory12;
    @Value("${word.FunctionalDesignDescription12}")
    private String functionalDesignDescription12;
    @Value("${word.SupplementaryInstruction12}")
    private String supplementaryInstruction12;
    @Value("${word.ExpectedToArtificial12}")
    private String expectedToArtificial12;

    @Value("${word.TestCaseName13}")
    private String testCaseName13;
    @Value("${word.LeadRole13}")
    private String leadRole13;
    @Value("${word.Precondition13}")
    private String precondition13;
    @Value("${word.PostCondition13}")
    private String postCondition13;
    @Value("${word.FunctionalCategory13}")
    private String functionalCategory13;
    @Value("${word.FunctionalDesignDescription13}")
    private String functionalDesignDescription13;
    @Value("${word.SupplementaryInstruction13}")
    private String supplementaryInstruction13;
    @Value("${word.ExpectedToArtificial13}")
    private String expectedToArtificial13;

    /**
     * 约束条件
     */
    @Value("${word.DevelopmentConstraints}")
    private String developmentConstraints;
    @Value("${word.ProgressOfTheTarget}")
    private String progressOfTheTarget;
    @Value("${word.QualityExpectations}")
    private String qualityExpectations;
    @Value("${word.StaffRequirement}")
    private String staffRequirement;
    @Value("${word.AcceptanceCriteria}")
    private String acceptanceCriteria;
    @Value("${word.MaintenanceRequirements}")
    private String maintenanceRequirements;
    @Value("${word.SecretAndConfidential}")
    private String secretAndConfidential;

    /**
     * 客户基本信息
     */
    @Value("${word.CustomerCompanyName}")
    private String customerCompanyName;
    @Value("${word.Linkman}")
    private String linkman;
    @Value("${word.ContactWay}")
    private String contactWay;
    @Value("${word.FinalUser}")
    private String finalUser;






}
