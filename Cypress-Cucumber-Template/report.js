
const fs = require("fs-extra");

const dir = "./report_Json/html";

if (fs.existsSync(dir)) {
  fs.emptyDirSync(dir);
}else{
  fs.mkdirSync(dir);
}


const reporter = require("cucumber-html-reporter");

const options = {
  theme: "bootstrap",
  jsonDir: "report_Json",
  output: "report_Json/html/cucumber_report.html",
  screenshotsDirectory: "screenshots/",
  storeScreenshots: true,
  reportSuiteAsScenarios: true,
  scenarioTimestamp: true,
  lauchReport: true,
  name: "cypress-cucumber-template",
  brandTitle: "smoke test",
  metadata: {
    "App Version": "0.3.2",
    "Test Environment": "STAGING",
    Browser: "Chrome  81.0.2840.98",
    Platform: "Windows 10",
    Parallel: "Scenarios",
    Executed: "Remote",
  },
};

reporter.generate(options);

