#Author: thang.pn@sutrixsolutions.com
#Keywords Summary : Create UserCases on EXCEL
@CheckUserCaseExcel
Feature: Execute test case on file excel
  Scenario: Check usercase on excel file test
    Then I get the file "CheckUsercaseOnExcel - Copy.xlsx" and I check usercase on sheet "Sheet1" and report to excel on row:
      | 1,3 |
