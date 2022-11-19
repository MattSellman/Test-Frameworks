const report = require("multiple-cucumber-html-reporter");
report.generate({
jsonDir: "report_Json",  // ** Path of .json file **//
reportPath: "./report_html/cucumber-html",
metadata: {
browser: {
name: "chrome",
version: "88",
},
device: "Local test machine",
platform: {
name: "Windows",
version: "Windows 10",
},
},
});