package StudentManage;

import java.util.*;

public class StudentManage{

	public StudentManage(){
		
	}
	
	public void start(){
		while(true){
			System.out.println("请选择:");
			System.out.println("1. 添加学生信息");
			System.out.println("2. 删除学生信息");
			System.out.println("3. 更改学生信息");
			System.out.println("4. 查找学生信息");
			System.out.println("0. 退出学生管理");

			Scanner sc = new Scanner(System.in);
			int op = sc.nextInt();
			switch(op){
				case 1:
					addStudent();
					break;
				case 2:
					deleteStudent();
					break;
				case 3:
					changeStudent();
					break;
				case 4:
					searchStudent();
					break;
				case 0:
					System.out.println("成功退出学生管理!");
					return;
				default:
					System.out.println("您的输入有误, 请重新输入!");
			}
		}
		
		
	}

	private void addStudent(){
		//调用工厂函数(参数1 生成学生), 生成学生对象, 再存储到数据库
		Student stu = 
		if(stu.store())
			System.out.println("添加学生信息成功!");
		else
			System.out.println("添加学生信息失败!");
	}

	private void deleteStudent(){
		System.out.println("请输入需要删除的学生信息的学号:");
		Scanner sc = new Scanner(System.in);
		int stuID = sc.nextInt();
		if(stuID < 0){
			System.out.println("您的输入有误!");
			return;
		}
		int res = deleteStudentFromDB(stuID);
		if(res == 0){
			System.out.println("删除成功!");
		}
		else if(res == 1){
			System.out.println("删除失败!");
		}
		else if(res == 2){
			System.out.println("取消操作成功!");
		}
		else if(res ==3){
			System.out.println("未找到该学生信息!");
		}
	}

	private int deleteStudentFromDB(int stuID){
		//从数据库中找到对应的学生信息删除
		//删除成功返回0
		//删除失败返回1
		//取消操作返回2
		//未找到学生信息返回3
		//需要进行二次确认(y/n), 同时显示该学生信息
		
	}

	private void changeStudent(){
		System.out.println("请输入需要更改的学生信息的学号:");
		Scanner sc = new Scanner(System.in);
		int stuID = sc.nextInt();
		if(stuID < 0){
			System.out.println("您的输入有误!");
			return;
		}
		int res = deleteStudentFromDB(stuID);
		if(res == 0){
			System.out.println("更改成功!");
		}
		else if(res == 1){
			System.out.println("更改失败!");
		}
		else if(res == 2){
			System.out.println("取消操作成功!");
		}
		else if(res == 3){
			System.out.println("未找到该学生信息!");
		}
		else if(res == 4){
			System.out.println("学生信息加载失败!");
		}
	}

	private int changeStudentFromDB(int stuID){
		//以stuID为参数new一个学生对象, 不是工厂出来的
		//将该学生信息从数据库中加载出来, 调用学生对象的加载函数
		//调用学生对象的更改函数, 完成对应的更改
		//最后, 在退出该函数前, 进行确认是否存储更改
		//如果确认, 调用学生对象的存储函数, 存储到数据库
		//更改成功返回0
		//更改失败返回1
		//取消操作返回2
		//未找到学生信息返回3
		//加载失败返回4
		//期间要显示该学生信息
		Student stu = new Student(stuID);
		int res = stu.load();
		if(res == 3 || res == 4)
			return res;
		stu.change();
		System.out.println("是否保存上述更改?(y/n)");
		Scanner sc = new Scanner(System.in);
		String choose = sc.next();
		while(true){
			if(choose.equals("y")){
			if(stu.store())
				return 0;
			else
				return 1;
			}
			else if(choose.equals("n")){
				return 2;
			}
			else{
				System.out.println("您的输入有误, 请重新输入!");
			}
		}
	}

	private void searchStudent(){
		System.out.println("请输入需要查找的学生信息的学号:");
		Scanner sc = new Scanner(System.in);
		int stuID = sc.nextInt();
		if(stuID < 0){
			System.out.println("您的输入有误!");
			return;
		}
		int res = searchStudentFromDB(stuID);
		if(res == 1){
			System.out.println("查找失败!");
		}
		else if(res == 3){
			System.out.println("未找到该学生信息!");
		}
	}

	private int searchStudentFromDB(int stuID){
		//查找失败返回1
		//未找到返回3
		//直接从数据库查找显示即可
		
	}
}
