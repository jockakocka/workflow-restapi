package com.example.restwt.services;

import com.example.restwt.models.*;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.*;

import static j2html.TagCreator.*;

@Service
public class MainService {

    public String getMaterialsFilePath()
    {
        String userDir = System.getProperty("user.dir");
        userDir+= File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator
                +"example"+File.separator+"restwt"+File.separator+"fileStorage"+File.separator+"Materials.txt";
        return userDir;
    }
    public String getFriendsFilePath()
    {
        String userDir = System.getProperty("user.dir");
        userDir+= File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator
                +"example"+File.separator+"restwt"+File.separator+"fileStorage"+File.separator+"Friends.txt";
        return userDir;
    }
    public String getFriendsBorrowedFilePath()
    {
        String userDir = System.getProperty("user.dir");
        userDir+= File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator
                +"example"+File.separator+"restwt"+File.separator+"fileStorage"+File.separator+"FriendsBorrowed.txt";
        return userDir;
    }
    public String getToolsFilePath()
    {
        String userDir = System.getProperty("user.dir");
        userDir+= File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator
                +"example"+File.separator+"restwt"+File.separator+"fileStorage"+File.separator+"Tools.txt";
        return userDir;
    }
    public String getOrdersFilePath()
    {
        String userDir = System.getProperty("user.dir");
        userDir+= File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator
                +"example"+File.separator+"restwt"+File.separator+"fileStorage"+File.separator+"Orders.txt";
        return userDir;
    }
    public String getInstructionsFilePath()
    {
        String userDir = System.getProperty("user.dir");
        userDir+= File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator
                +"example"+File.separator+"restwt"+File.separator+"fileStorage"+File.separator+"Instructions.txt";
        return userDir;
    }
    public String showMaterial(String model, String brokenPart) throws IOException {

        File file = new File(getMaterialsFilePath());
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        st=br.readLine(); //The names of the cells
        while ((st = br.readLine()) != null)
        {
            List<String> rowVariables= Arrays.asList(st.split(","));
            if(rowVariables.get(2).equals(model))
            {
                if (rowVariables.get(0).contains(brokenPart))
                {
                    return rowVariables.get(0);
                }

            }
        }
        return "That part does not exist";
    }
    public Material orderingChoice(String model, String brokenPart) throws IOException {
        File file = new File(getMaterialsFilePath());
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        st=br.readLine(); //The names of the cells
        while ((st = br.readLine()) != null)
        {
            List<String> rowVariables= Arrays.asList(st.split(","));
            if(rowVariables.get(2).equals(model))
            {
                if (rowVariables.get(0).contains(brokenPart))
                {
                    return new Material(rowVariables.get(0),Integer.parseInt(rowVariables.get(1)),rowVariables.get(2));
                }

            }
        }

        return new Material("No found materials",0,"No phone");
    }

    public Friend findFriend(String model, String brokenPart) throws IOException {
        File file = new File(getFriendsFilePath());
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        st = br.readLine(); //The names of the cells
        while ((st = br.readLine()) != null) {
            List<String> rowVariables = Arrays.asList(st.split(","));
            for (int i = 1; i < rowVariables.size(); i++) {
                if (rowVariables.get(i).contains(brokenPart)) {
                    if (rowVariables.get(i).replaceAll(" ", "").toLowerCase().contains(model.toLowerCase())) {
                        return new Friend(rowVariables.get(0), rowVariables.get(1), rowVariables.get(2), rowVariables.get(3), rowVariables.get(4));
                    }
                }
            }
        }
        return new Friend("No friend found", "", "", "", "");
    }


    public Boolean addInFriendsBorrowed(String friendsName, String toolOrMaterial) throws IOException {
        File file = new File(getFriendsBorrowedFilePath());
        FileWriter writer = new FileWriter(file,true);
        writer.write(friendsName+","+toolOrMaterial+"\n");
        writer.close();
        return true;
    }

    public String addOrder(String toolOrMaterialName,Boolean buyOrBorrow) throws IOException {
        File file = new File(getOrdersFilePath());
        FileWriter writer = new FileWriter(file,true);
        Random random=new Random();
        Long orderID=Long.parseLong(String.valueOf(random.nextInt(1000)));
        writer.write(orderID+","+toolOrMaterialName+","+buyOrBorrow+"\n");
        writer.close();
        return orderID.toString();
    }

    public String showTool(String model, String brokenPart) throws IOException {
        File file = new File(getToolsFilePath());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String st;
        st=bufferedReader.readLine(); //The names of the cells
        while ((st = bufferedReader.readLine()) != null)
        {
            List<String> rowVariables= Arrays.asList(st.split(","));
            String whichParts=rowVariables.get(2);
            String searchString = brokenPart + " for ";
            if(model.equals("Iphone11"))
            {
                searchString+="Iphone 11";
            }
            else if(model.equals("Iphone12"))
            {
                searchString+="Iphone 12";
            }
            else if(model.equals("SamsungS8"))
            {
                searchString+="Samsung s8";
            }
            else if(model.equals("SamsungS7"))
            {
                searchString+="Samsung s7";
            }
            else if(model.equals("HuaweiP40"))
            {
                searchString+="Huawei p40";
            }
            else if(model.equals("HuaweiP20"))
            {
                searchString+="Huawei p20";
            }
            else if(model.equals("OnePlus8T"))
            {
                searchString+="OnePlus 8T";
            }
            else if(model.equals("OnePlus7T"))
            {
                searchString+="OnePlus 7T";
            }
            if(whichParts.contains(searchString))
            {
                return rowVariables.get(0);
            }
        }
        return "No tool for that broken part and model";
    }

    public Tool orderingChoiceTool(String model, String brokenPart) throws IOException {
        File file = new File(getToolsFilePath());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String st;
        st=bufferedReader.readLine(); //The names of the cells
        while ((st = bufferedReader.readLine()) != null)
        {
            List<String> rowVariables= Arrays.asList(st.split(","));
            String whichParts=rowVariables.get(2);
            String searchString = brokenPart + " for ";
            if(model.equals("Iphone11"))
            {
                searchString+="Iphone 11";
            }
            else if(model.equals("Iphone12"))
            {
                searchString+="Iphone 12";
            }
            else if(model.equals("SamsungS8"))
            {
                searchString+="Samsung s8";
            }
            else if(model.equals("SamsungS7"))
            {
                searchString+="Samsung s7";
            }
            else if(model.equals("HuaweiP40"))
            {
                searchString+="Huawei p40";
            }
            else if(model.equals("HuaweiP20"))
            {
                searchString+="Huawei p20";
            }
            else if(model.equals("OnePlus8T"))
            {
                searchString+="OnePlus 8T";
            }
            else if(model.equals("OnePlus7T"))
            {
                searchString+="OnePlus 7T";
            }

            if(whichParts.contains(searchString))
            {
                return new Tool(rowVariables.get(0),Integer.parseInt(rowVariables.get(1)));
            }
        }
        return new Tool("No tool for that broken part and model",0);
    }

    public Friend findFriend(String toolName) throws IOException {
        File file = new File(getFriendsFilePath());
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        st = br.readLine(); //The names of the cells
        while ((st = br.readLine()) != null) {
            List<String> rowVariables = Arrays.asList(st.split(","));
            for (int i = 1; i < rowVariables.size(); i++) {
               if(rowVariables.get(i).contains(toolName))
               {
                   return new Friend(rowVariables.get(0), rowVariables.get(1), rowVariables.get(2), rowVariables.get(3), rowVariables.get(4));
                }
            }
        }
        return new Friend("No friend found for that tool", "", "", "", "");
    }

    public String showInstructions(String model, String brokenPart) throws IOException {
        String instructions="The following instructions are for repairment of the smartphone: "+model+ " and the broken part is: "+brokenPart+". ";
        instructions+="1. Shut down the smartphone ";
        instructions+="2. Remove the battery ";
        instructions+="3. Put the new received part: " + brokenPart+" ";
        instructions+="4. Watch the video";
        String tutorialLink;
        if(model.equals("Iphone11"))
            tutorialLink="https://www.youtube.com/watch?v=jtl4KKRIheo";
        else if(model.equals("Iphone12"))
            tutorialLink="https://www.youtube.com/watch?v=FY7DtKMBxBw";
        else if(model.equals("SamsungS8"))
            tutorialLink="https://www.youtube.com/watch?v=GRmY-4HKVIk";
        else if(model.equals("SamsungS7"))
            tutorialLink="https://www.youtube.com/watch?v=A_S6pL5pznA";
        else if(model.equals("HuaweiP40"))
            tutorialLink="https://www.youtube.com/watch?v=M81-gS1zUj0";
        else if(model.equals("HuaweiP20"))
            tutorialLink="https://www.youtube.com/watch?v=VOZzKeGj9Zw";
        else if(model.equals("OnePlus8T"))
            tutorialLink="https://www.youtube.com/watch?v=kjt60g5qQ_A";
        else
            tutorialLink="https://www.youtube.com/watch?v=_PySmMrihWI";
        instructions+=" "+tutorialLink+" ";
        instructions+=" Your phone should be finished now.";
        File file = new File(getInstructionsFilePath());
        FileWriter writer = new FileWriter(file,true);
        writer.write(instructions+","+model+","+brokenPart+"\n");
        writer.close();
    return instructions;
    }

    public Integer timeToFix(String role, String org, String html) {
        Random random=new Random();
        return random.nextInt(50);
    }

    public Boolean repairWorked(String role, String org, String html) {
        Random random =new Random();
        return random.nextBoolean();
    }

    public Wrapper prepareData(Boolean repairWorked) {
        String reason;
        if(repairWorked)
            reason="It worked";
        else
            reason="It did not work";
        return new Wrapper(reason,"Still not known","Still no one","Still empty",false);
    }

    public Wrapper createReview(String Role,String Org,String HTML,Boolean repairWorked) {
        Random random=new Random();
        String reason;
        String review;
        if(repairWorked)
            reason="It worked";
        else
            reason="It did not work";
        if(repairWorked)
            review="The instructions were very great "+random.nextInt(5000);
        else
            review="The instructions were not great "+random.nextInt(5000);
        return new Wrapper(reason,"Still not known","Still no one",review,false);
    }
    public Wrapper createSendVideoAndReview(String Role,String Org,String HTML,String reason,String review)
    {
        Random random =new Random();
        return new Wrapper(reason,"Still not known","Still no one",review,random.nextBoolean());
    }
    public Wrapper createVideoName(String Role,String Org,String HTML)
    {
        Random random =new Random();
        String videoName="VideoName + "+random.nextInt(5000);
        return new Wrapper("reason",videoName,"Still no one","review",true);
    }
    public Wrapper createReasonNotWorked(String Role,String Org,String HTML)
    {
        String reason="Did not work as expected.";
        return new Wrapper(reason,"No video because it did not work","Still no one","Didnt work",false);
    }

    public Wrapper createRepairManName(String role, String org, String html) {
        String[] list = {"Alek","Peter","Lucas","Stjepan","Niklas","Simon"};
        Random random=new Random();
        return new Wrapper("It did not wok as expected","No video because it did not work",list[random.nextInt(6)],
                "Didnt work",false);
    }

    public WrapperModelBrokenPart createModelAndPart(String role, String org, String html) {
        String[] listModel={"Iphone11","Iphone12","HuaweiP40","HuaweiP20","SamsungS8","SamsungS7","OnePlus8T","OnePlus7T"};
        String[] parts={"GPU","CPU","Battery","Charger Port","Screen"};
        Random random=new Random();
        return new WrapperModelBrokenPart(listModel[random.nextInt(8)],parts[random.nextInt(5)]);
    }

    public String buyIt(String role, String org, String html) {
        Random random = new Random();
        boolean bool=random.nextBoolean();
        if(bool)
            return "Yes I will buy it";
        else return "No I wont buy it";
    }

    public String time(String role, String org, String html) {
        Random random =new Random();
        return String.valueOf(random.nextInt(50));
    }
    public Boolean receive(MultiValueMap<String, String> headers, Map<String, String> body)
    {
        for(String str : headers.keySet()){
            System.out.println(str+ "->"+headers.getFirst(str));
        }
        for(String str : body.keySet())
        {
            System.out.println("BODY");
            System.out.println(str+"->"+body.get(str));
        }
        String CPEECallback=headers.getFirst("CPEE-CALLBACK");
        String label=headers.getFirst("CPEE-LABEL");
        String role=body.get("Role");
        String Org=body.get("Org");
        String HTML=body.get("HTML");
        Task task = new Task(CPEECallback,label,role,Org,HTML,false);
        String userDir = System.getProperty("user.dir");
        userDir+= File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator
                +"example"+File.separator+"restwt"+File.separator+"tasks"+File.separator+role+"_"+label+"_"+CPEECallback.split("/")[7]+".txt";
        System.out.println(userDir);
        File taskFile = new File(userDir);
        try{
            taskFile.createNewFile();
            FileWriter writer = new FileWriter(taskFile);
            writer.write(task.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public String worklist(String name) throws IOException {
        String userDir = System.getProperty("user.dir");
        userDir+= File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator
                +"example"+File.separator+"restwt"+File.separator+"organisation.xml";
        File organisation=new File(userDir);
        String namePerson;
        String role = "";
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(organisation);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("subject");
            for (int itr = 0; itr < nodeList.getLength(); itr++)
            {
                Node node = nodeList.item(itr);
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) node;
                    System.out.println("Relation->"+ eElement.getElementsByTagName("relation").item(0).getTextContent());
                    if(eElement.getAttribute("id").equals(name))
                    {
                        role=eElement.getElementsByTagName("relation") .item(0).getAttributes().getNamedItem("role").getTextContent();
                    }
                    System.out.println("Name->"+name);
                    System.out.println("ROLE->"+role);


                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        String tasksDir = System.getProperty("user.dir");
        tasksDir+= File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator
                +"example"+File.separator+"restwt"+File.separator+"tasks";
        File folder=new File(tasksDir);
        String[] fileNames=folder.list();
        List<String> tasksForUser=new ArrayList<>();
        List<Task> tasks=new ArrayList<>();
        if(fileNames!=null)
        for(String string:fileNames)
        {
            if(string.contains(role))
            {
                tasksForUser.add(string);
            }
        }
        if(!tasksForUser.isEmpty())
            for(String string : tasksForUser)
            {
                File taskFile=new File(tasksDir+File.separator+string);
                BufferedReader br = new BufferedReader(new FileReader(taskFile));

                String st;
                while ((st = br.readLine()) != null)
                {
                    String[] temp=st.split(",");
                    if(!Boolean.parseBoolean(temp[5]))
                    tasks.add(new Task(temp[0],temp[1],temp[2],temp[3],temp[4],Boolean.parseBoolean(temp[5])));

                }
            }
        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n";
        for(Task task :tasks)
            html += "<a href=\"" + "http://abgabe.cs.univie.ac.at:9010/work?callbackURL="+task.getCallbackURL()+"&htmlForm="+task.getHTML()+ "\">" + task.getLabel() + "</a>\n";
               html+= "</body>\n" +
                "</html>";
        return html;
    }

    public Boolean finalFile(Map<String, String> params) throws IOException {
        String tasksDir = System.getProperty("user.dir");
        tasksDir+= File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator
                +"example"+File.separator+"restwt"+File.separator+"tasks";

        File folder=new File(tasksDir);
        String[] fileNames=folder.list();
        for(String fileName : fileNames)
        {
            if(fileName.contains(params.get("callbackURL").split("/")[7]))
            {
                File file = new File(tasksDir+File.separator+fileName);
                String fileContext = FileUtils.readFileToString(file);
                fileContext = fileContext.replaceAll("false", "true");
                FileUtils.write(file, fileContext);
            }
        }
        for(String str : params.keySet())
        {
            System.out.println(str+"->"+params.get(str));
        }

            JSONObject obj=new JSONObject();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if(!entry.getKey().equals("callbackURL"))
                obj.put(entry.getKey(),entry.getValue());
            }
            URL url = new URL(params.get("callbackURL"));
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setRequestProperty("Content-Type", "application/json");
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("PUT");
            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write(obj.toString());
            out.close();
            httpCon.getInputStream();

        return true;
    }
    public String work(String callbackURL,String htmlForm) throws IOException {
        String tasksDir = System.getProperty("user.dir");
        tasksDir+= File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator
                +"example"+File.separator+"restwt"+File.separator+"tasks";

        File folder=new File(tasksDir);
        String[] fileNames=folder.list();
        for(String fileName : fileNames)
        {
            if(fileName.contains(callbackURL.split("/")[7]))
            {
                File file = new File(tasksDir+File.separator+fileName);
                String fileContext = FileUtils.readFileToString(file);
                fileContext = fileContext.replaceAll("false", "true");
                FileUtils.write(file, fileContext);
            }
        }
        URLConnection connection =  new URL(htmlForm).openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String content="";
        String temp;
        while((temp=bufferedReader.readLine())!=null)
        {
            if(temp.contains("</form>"))
            {
                content+=" <input type=\"hidden\" id=\"callbackURL\" name=\"callbackURL\" value=\""+callbackURL+"\">";
            }
                content+=temp;

        }
        bufferedReader.close();
        return "Contents of the web page: "+content;

    }

    public Boolean giveBack(Map<String, String> params) throws IOException {
        String tasksDir = System.getProperty("user.dir");
        tasksDir+= File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator
                +"example"+File.separator+"restwt"+File.separator+"tasks";

        File folder=new File(tasksDir);
        String[] fileNames=folder.list();
        for(String fileName : fileNames)
        {
            if(fileName.contains((params.get("callbackURL").split("/")[7])))
            {
                File file = new File(tasksDir+File.separator+fileName);
                String fileContext = FileUtils.readFileToString(file);
                fileContext = fileContext.replaceAll("true", "false");
                FileUtils.write(file, fileContext);
                return true;
            }
        }
        return false;
    }
}
