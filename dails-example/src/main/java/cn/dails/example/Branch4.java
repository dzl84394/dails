package cn.dails.example;


import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;

/**
 * mybatisplus +bootstarp
 */
public class Branch4 extends Example {

    static String defFile = "branch4";
    @Override
    public void initFiles() {
        File rootfile = new File(getRooturl());
        // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!rootfile.exists()) {
            System.err.println("项目根路径不存在");
            return;
        }
        String workUrl = getWorkUrl();



        File javafile = new File(workUrl);
        if (!javafile.exists()) {// 如果不存在java目录，创建它
            javafile.mkdirs();
        }

        Tools.mkFile(workUrl + "/controller");
        Tools.mkFile(workUrl + "/conf");
        Tools.mkFile(workUrl + "/bean");
        Tools.mkFile(workUrl + "/bean/vo");
        Tools.mkFile(workUrl + "/controller");
        Tools.mkFile(workUrl + "/dao");
        Tools.mkFile(workUrl + "/dao/entity");
        Tools.mkFile(workUrl + "/service");
        Tools.mkFile(workUrl + "/service/impl");
//        Tools.mkFile(workUrl + "/domain");
        Tools.mkFile(workUrl + "/utils");

        Tools.mkFile(getRooturl() + "/src/main/resources/mappers");
        Tools.mkFile(getRooturl() + "/src/main/resources/static/pms/utils");
        Tools.mkFile(getRooturl() + "/src/main/resources/templates");
        Tools.mkFile(getJsUrl());
        addLauncher();
        addConf();
        String utiljs = getRooturl() + "/src/main/resources/static/pms/utils/util.js";
        File utiljsfile = new File(utiljs);
        createUtilJs(utiljsfile,defFile+"/views/util.js");
    }



    @Override
    public void initJsp() {
        String name = getEntityClass().getSimpleName();
        String name2 = name.substring(0, 1).toLowerCase() + name.substring(1);
        if (name2.indexOf("Entity")>=0){
            name2 = name2.replace("Entity","");
        }

        String urlString = getRooturl() + "/src/main/resources/templates/" + name2;
        Tools.mkFile(urlString);// 添加文件夹

        File indexFile = new File(urlString + "/index.html");
        File newFile = new File(urlString + "/new.html");
        File editFile = new File(urlString + "/edit.html");
        File showFile = new File(urlString + "/show.html");
        createJsp(indexFile,name2, defFile+"/views/index.html");
        createJsp(newFile,name2, defFile+"/views/new.html");
        createJsp(editFile, name2,defFile+"/views/edit.html");
        createJsp(showFile, name2,defFile+"/views/show.html");
        urlString = getJsUrl() +"/" ;
        File jsFile = new File(urlString +name2+".js");
        createJs(jsFile, name2,defFile+"/views/obj.js");



    }

    private void addLauncher() {
        // branch1 添加 Launcher.java

        File file = new File(getWorkUrl() + "/"+getServiceName()+"Launcher.java");
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("新增文件: " + file.getName());
                BufferedWriter output = new BufferedWriter(new FileWriter(file));
                String string = readFile(defFile+"/Application.txt");

                String myurl = getUrl().replaceAll("/", ".");
                string = string.replaceAll("\\$\\{url\\}", myurl);
                string = string.replaceAll("\\$\\{serviceName\\}", getServiceName());
                output.write(string);
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("不存在路径：" + getWorkUrl());
            }
        } else {
            System.out.println("已存在: " + file.getName());
        }

    }


    public void addController() {
        System.out.println(System.getProperty("user.dir"));// user.dir指定了当前的路径

        String domainUrl = getEntityClass().getPackage().getName().toString();
        String name = replaceEntity(getEntityClass().getSimpleName());

        File controllerFile = new File(getWorkUrl() + "/controller/" + replaceEntity(name) + "Controller.java");
        createFile(controllerFile, domainUrl, name, defFile+"/ObjController.txt");

        File requestVo = new File(getWorkUrl() + "/bean/vo/" + replaceEntity(name) + "RequestVo.java");
        createFile(requestVo, domainUrl, name, defFile+"/RequestVo.txt");

        File responseVo = new File(getWorkUrl() + "/bean/vo/" + replaceEntity(name) + "ResponseVo.java");
        createFile(responseVo, domainUrl, name, defFile+"/ResponseVo.txt");

    }

    public void addService() {
        String domainUrl = getEntityClass().getPackage().getName().toString();
        String name = replaceEntity(getEntityClass().getSimpleName());
        String workUrl = getWorkUrl();

        File iServiceFile = new File(workUrl + "/service/I" + name + "Service.java");
        File serviceimplFile = new File(workUrl + "/service/impl/" + name + "Service.java");
        createFile(iServiceFile, domainUrl, name, defFile+"/IObjService.txt");
        createFile(serviceimplFile, domainUrl, name, defFile+"/ObjService.txt");
    }

    public void addDao() {
        String domainUrl = getEntityClass().getPackage().getName().toString();
        String name = replaceEntity(getEntityClass().getSimpleName());

        File daoFile = new File(getWorkUrl() + "/dao/" + name + "Dao.java");
        createFile(daoFile, domainUrl, name, defFile+"/ObjDao.txt");

        File mapperFile = new File(getResourceUrl() + "/mappers/" + name + "Mapper.xml");
        createFile(mapperFile, domainUrl, name, defFile+"/Mapper.txt");



    }

    public void addConf() {
        String workUrl = getWorkUrl();
        Tools.mkFile(workUrl + "/conf");
        File metaHandler = new File(workUrl + "/conf/MetaHandler.java" );
        File mybatisPlusConfig = new File(workUrl + "/conf/MybatisPlusConfig.java" );
        createConf(metaHandler, defFile+"/MetaHandler.txt");

        createConf(mybatisPlusConfig, defFile+"/MybatisPlusConfig.txt");



    }

    private void createConf(File file, String mobanUrl) {
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("createNewFile: " + file.getName());
                String name = file.getName();
                BufferedWriter output = new BufferedWriter(new FileWriter(file));

                String string = readFile(mobanUrl);

                output.write(string);
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("已存在JS: " + file.getName());
        }
    }


    private void createFile(File file, String domainUrl, String name, String mobanUrl) {
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("createNewFile: " + file.getName());

                String name2 = name.substring(0, 1).toLowerCase() + name.substring(1);


                BufferedWriter output = new BufferedWriter(new FileWriter(file));

                String string = readFile(mobanUrl);
                string = string.replaceAll("\\$\\{Objname\\}", name);
                string = string.replaceAll("\\$\\{objname\\}", name2);
                string = string.replaceAll("\\$\\{domainUrl\\}", domainUrl);

                String myurl = getUrl().replaceAll("/", ".");
                string = string.replaceAll("\\$\\{url\\}", myurl);
                output.write(string);
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("路径不存在，请先执行Dails.getInstance().addFiles()");
            }
        } else {
            System.out.println("已存在JAVA: " + file.getName());
        }
    }

    private void createJsp(File file,String ObjName, String mobanUrl) {
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("createNewFile: " + file.getName());
                String name = file.getName();
                BufferedWriter output = new BufferedWriter(new FileWriter(file));

                String string = readFile(mobanUrl);

                String[] filedNames = getFiledName();
                String temp = "";
                String temp2 = "";
                if (name.equals("index.html")) {
                    for (int i = 0; i < filedNames.length; i++) {
                        if (!filedNames[i].equals("serialVersionUID")) {
                            temp += "								<td>" + filedNames[i] + "</td>\n";
                            temp2 += "										<td th:text='\\$\\{s." + filedNames[i] + "\\}'></td>\n";
                        }
                    }

                    string = string.replaceAll("\\$\\{tr1\\}", temp);
                    string = string.replaceAll("\\$\\{tr2\\}", temp2);
                }
                if (name.equals("new.html")) {
                    for (int i = 0; i < filedNames.length; i++) {
                        if (!filedNames[i].equals("serialVersionUID") && !filedNames[i].equals("id")) {
                            temp +=   "               <div class='form-group col-lg-12'> \n"
                                    + "						<label for='"+filedNames[i]+"' class='col-sm-2 control-label'>"+filedNames[i]+"</label>\n"
                                    + "						<div class='col-sm-10'>\n"
                                    + "							<input type='text' class='form-control' name='"+filedNames[i]+"' id='"+filedNames[i]+"' th:value='${obj."+filedNames[i]+"}'/>\n"
                                    + "						</div>\n"
                                    + "					</div>\n";
                        }
                    }
                    string = string.replace("newForm", temp);
                }
                if (name.equals("show.html")) {
                    for (int i = 0; i < filedNames.length; i++) {

                        if (!filedNames[i].equals("id") && !filedNames[i].equals("serialVersionUID")) {
                            temp += "                <div class=\"form-group\">\n" +
                                    "                    <label class=\"col-xs-4 col-sm-2 control-label\">"+filedNames[i]+"</label>\n" +
                                    "                    <div class=\"col-xs-8 col-sm-10\">\n" +
                                    "                        <p class=\"form-control-static\" id='"+filedNames[i]+"' th:text='${obj."+filedNames[i]+"}'></p>\n" +
                                    "                    </div>\n" +
                                    "                </div>\n" ;

                        }
                    }
                    string = string.replace("showForm", temp);
                }
                String objName = ObjName.substring(0, 1).toLowerCase() + ObjName.substring(1);

                string = string.replaceAll("\\{objname\\}", objName);
                string = string.replaceAll("\\{Objname\\}", ObjName);

                output.write(string);
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("已存在JSP: " + file.getName());
        }
    }

    private void createUtilJs(File file, String mobanUrl) {
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("createNewFile: " + file.getName());
                String name = file.getName();
                BufferedWriter output = new BufferedWriter(new FileWriter(file));

                String string = readFile(mobanUrl);

                output.write(string);
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("已存在JS: " + file.getName());
        }
    }



    private void createJs(File file,String ObjName, String mobanUrl) {
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("createNewFile: " + file.getName());
                String name = file.getName();
                BufferedWriter output = new BufferedWriter(new FileWriter(file));

                String string = readFile(mobanUrl);

                String[] filedNames = getFiledName();
                String temp1 = "\"<tr>\"\n";
                String temp2 = "";
                String temp3 = "";
                String temp4 = "";

                for (int i = 0; i < filedNames.length; i++) {
                    if (!filedNames[i].equals("serialVersionUID")) {
                        temp1 += "                 + \"<td>\"+index."+filedNames[i]+"+\"</td>\"\n";
                        temp2 += "  data."+filedNames[i]+" = $('#"+filedNames[i]+"').val();\n";
                        temp3 += "  $(\"#"+filedNames[i]+"\").text( index."+filedNames[i]+");\n";
                        temp4 += "  $(\"#"+filedNames[i]+"\").val( index."+filedNames[i]+");\n";
                    }
                }


                String objName = ObjName.substring(0, 1).toLowerCase() + ObjName.substring(1);
                string = string.replace("{temp1}", temp1);
                string = string.replace("{temp2}", temp2);
                string = string.replace("{temp3}", temp3);
                string = string.replace("{temp4}", temp4);
                string = string.replaceAll("\\{objname\\}", objName);
                string = string.replaceAll("\\{Objname\\}", ObjName);



                output.write(string);
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("已存在JS: " + file.getName());
        }
    }
    @Override
    public void initJava() {
        addController();
        addDao();
        addService();

    }
    private String[] getFiledName() {
        Field[] fields = getEntityClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    public String readFile(String url) {
        File file;
        String ss = "";
        try {
            URL url2 = this.getClass().getResource(url);


            ClassPathResource resource = new ClassPathResource(url);
            InputStream inputStream = resource.getInputStream();
            ss = Tools.inputStream2String(inputStream);

			/*file = ResourceUtils.getFile(url2);//
			FileReader reader = new FileReader(file);// 定义一个fileReader对象，用来初始化BufferedReader
			BufferedReader bReader = new BufferedReader(reader);// new一个BufferedReader对象，将文件内容读取到缓存
			String s = "";
			while ((s = bReader.readLine()) != null) {// 逐行读取文件内容，不读取换行符和末尾的空格
				sb.append(s + "\n");// 将读取的字符串添加换行符后累加存放在缓存中
			}
			bReader.close();*/
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ss;
    }


    public String replaceEntity(String name){
        if (name.indexOf("Entity")>=0){
            name = name.replace("Entity","");
        }
        return name;
    }


}
