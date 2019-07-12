package Manage.SuperiorManage;

import entity.Person.*;
import entity.factory.*;
import java.util.*;

public class SuperiorManage{
	private static factory fac = new factory();
	public SuperiorManage(){
		
	}

	public void start(){
		while(true){
			System.out.println("请选择:");
			System.out.println("1. 添加职工信息");
			System.out.println("2. 删除职工信息");
			System.out.println("3. 更改职工信息");
			System.out.println("4. 查找职工信息");
			System.out.println("0. 退出");

			Scanner sc = new Scanner(System.in);
			int op = sc.nextInt();
			switch(op){
				case 1:
					addSuperior();
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 0:
					System.out.println("成功退出职工管理!");
					return;
				default:
					System.out.println("您的输入有误, 请重新输入!");
			}
		}
	}

	private void addSuperior(){
		Superior sup = (Superior)(fac.addPerson(2));
		System.out.println("您要添加的职工信息为:");
		//sup.show();//非数据库操作函数
		System.out.println("确认添加吗?(y/n)");
		Scanner sc = new Scanner(System.in);
		String choose = sc.next();
		while(true){
			if(choose.equals("y")){
				//int res = sup.store();
				/* if(res==0) */
					// System.out.println("添加职工信息成功!");
				// else if(res==1)
					// System.out.println("添加职工信息失败!");
				// else if(res==2)
					// System.out.println("工号重复, 添加职工信息失败!");
				break;
			}
			else if(choose.equals("n")){
				System.out.println("取消操作成功!");
				break;
			}
			else{
				System.out.println("您的输入有误, 请重新输入!");
			}
		}
	}

	private void deleteSuperior(){
		System.out.println("请输入需要删除的职工信息的工号:");
		Scanner sc = new Scanner(System.in);
		int supID = sc.nextInt();
		if(supID < 0){
			System.out.println("您的输入有误!");
			return;
		}
		int res = deleteSuperiorInDB(supID);
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
			System.out.println("未找到该工号对应的职工信息!");
		}
	}

	private int deleteSuperiorInDB(int supID){
		Superior sup = new Superior();
		/* if(sup.search(supID)==0){ */
			// System.out.println("您要删除的职工信息为:");
			// sup.display(supID);
			// System.out.println("确认删除吗?(y/n)");
			// Scanner sc = new Scanner(System.in);
			// String choose = sc.next();
			// while(true){
				// if(choose.equals("y")){
					// if(sup.delete(supID)==0)
						// return 0;
					// else
						// return 1;
				// }
				// else if(choose.equals("n")){
					// return 2;
				// }
				// else{
					// System.out.println("您的输入有误, 请重新输入!");
				// }
			// }
		// }
		/* else */
			return 3;
	}

	private void changeSuperior(){
		System.out.println("请输入需要更改的职工信息的工号:");
		Scanner sc = new Scanner(System.in);
		int supID = sc.nextInt();
		if(supID < 0){
			System.out.println("您的输入有误!");
			return;
		}
		int res = changeSuperiorInDB(supID);
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
			System.out.println("未找到该工号对应的职工信息!");
		}
		else if(res == 4){
			System.out.println("该工号对应的职工信息加载失败!");
		}
	}

	private int changeSuperiorInDB(int supID){
		// Superior sup = new Superior(supID);
		// int res = sup.load();
		/* if(res == 2) */
			// return 3;
		// if(res == 1)
			/* return 4; */
		// sup.change();//非数据库的操作
		System.out.println("更改后的职工信息为:");
		// sup.show();//非数据库的操作
		System.out.println("确认更改吗?(y/n)");
		Scanner sc = new Scanner(System.in);
		String choose = sc.next();
		while(true){
			if(choose.equals("y")){
				/* if(sup.update()==0)//注意这里是update函数 */
					// return 0;
				// else
					/* return 1; */
			}
			else if(choose.equals("n")){
				return 2;
			}
			else{
				System.out.println("您的输入有误, 请重新输入!");
			}
		}
	}

	private void searchSuperior(){
		System.out.println("请输入需要查找的职工信息的工号:");
		Scanner sc = new Scanner(System.in);
		int supID = sc.nextInt();
		if(supID < 0){
			System.out.println("您的输入有误!");
			return;
		}
		int res = searchSuperiorInDB(supID);
		if(res == 1){
			System.out.println("查找失败!");
		}
		else if(res == 3){
			System.out.println("未找到该工号对应的职工信息!");
		}
	}

	private int searchSuperiorInDB(int supID){
		Superior sup = new Superior();
		/* int res = sup.search(supID); */
		// if(res==0){
			// System.out.println("该工号对应的职工信息为:");
			// sup.display(supID);
			// return 0;
		// }
		// else if(res==1)
			// return 1;
		/* else */
			return 3;
	}
}
