const fs = require("fs-extra");
RemoveDir("./report_Json")
RemoveDir("./report_Json/html")

function RemoveDir(DirectoryName){
    if (!fs.existsSync(DirectoryName)) {
        fs.mkdirSync(DirectoryName);
    }
      
    fs.readdir(DirectoryName,function(err,files) {
        if(err){
                console.log('Unable to scan directory: ' + err);
        }
        files.forEach(function(file){
            fs.unlink(DirectoryName + '/' + file, function(err){
            })
        })
      });
}
