call mvn clean -DskipTests package
xcopy ".\src\main\webapp\*.*" ".\target\classes\src\main\webapp\"  /S /D /Y /Q >nul
for /r .\target\repo\ %%i in (*.jar) do copy %%i .\target\classes\*.jar /y >nul
copy .\target\*.jar .\target\classes\ >nul
cd target\classes
java -classpath ".;*" com.gitee.drinkjava2.reactmrp.MainApp
@pause