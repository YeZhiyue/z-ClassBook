package cn.ye.UtilTest;

import org.junit.Test;

public class StringTest {
    @Test
    public void user1() {
        String[] split = user.split(",");
        for (String s : split) {
//            System.out.println("var " + s + "=date." + s + ";");
            System.out.print(s + ",");
            //(null,'叶之越','000000','739253436@qq.com','男','1569201209','head_img0','739153436','15669201209','物联网172','1998-12-28 17:47:08','2020-04-11 17:47:08'),
        }
    }

    @Test
    public void img() {
        String[] split = user.split(",");
        for (String s : split) System.out.println("var " + s + "=date." + s + ";");
    }

    @Test
    public void perEmail() {
        String[] split = perEmail.split(",");
        for (String s : split)
            System.out.println(s + "=perEmail[i]." + s + ";");
//            System.out.println("var " + s + ";");
    }

    @Test
    public void perPassage() {
        String[] split = user.split(",");
        for (String s : split) System.out.println("var " + s + "=date." + s + ";");
    }

    @Test
    public void concatStr() {
        String all = user + "," + img + "," + perEmail + "," + perPassage;
        for (String s : all.split(",")) {
            System.out.print("private String " + s + "; ");
        }
    }

    @Test
    public void fileOut(){
        String n="王李张刘陈杨黄赵吴周徐孙叶熊";
        String n2="三四五六七黄赵吴";
        int s=1000;
        int x4=1;
        int x90=30;
        int img=0;
        for (int i=1; i < 6; i++) {
            for (int j = 1; j < 6; j++) {
//                System.out.println("(null,'"+n.charAt(i)+n2.charAt(j)+"','00000000','73925"+s+++"@qq.com','男','156920"+s+++"'" +
//                        ",'head_img"+img+++"','739"+s+++"36','飞来飞去','上蹿下跳','156692"+s+++"9','物联网17"+x4+++"','19"+x90+++"-12-28 17:47:08','2020-04-14 17:47:08'),");
//                System.out.println("(null, 'head_img_"+x4+++".jpg', '头像', 'true'),");
//                System.out.println("(null, "+i+", "+j+", '19"+x90+++"-12-28 17:47:08', '"+n.charAt(i)+n2.charAt(j)+"发送了一条私信给你！'),");
                System.out.println("(null, "+i+", '2020-4-0"+j+" 17:47:08', '"+n.charAt(i)+n2.charAt(j)+"班级写了一条留言板1！'),");
            }
        }

    }

    private String user = "id,name,password,email,sex,phone,img,qq,wChat,className,birthday,login_data,perEmail,perPassages,hobby,introduce";
//    private String img = "id,img_name,img_type,is_use";
//    private String perEmail = "id,user_from,user_to,date,content";
    private String perPassage = "id,edit_user,date,content";

    private String id;
    private String name;
    private String password;
    private String email;
    private String sex;
    private String phone;
    private String img;
    private String qq;
    private String wChat;
    private String className;
    private String birthday;
    private String login_data;
    private String perEmail;
    private String perPassages;
    private String hobby;
    private String introduce;
//    private String id;
    private String img_name;
    private String img_type;
    private String is_use;
//    private String id;
    private String user_from;
    private String user_to;
    private String date;
    private String content;
//    private String id;
    private String edit_user;
//    private String date;
//    private String content;


}
