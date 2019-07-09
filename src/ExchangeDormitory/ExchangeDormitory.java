package ExchangeDormitory;

import java.util.*;

public class ExchangeDormitory{
	public ExchangeDormitory(){
		
	}

	public void start(){
		while(true){
			System.out.println("请选择:");
			System.out.println("1. 分配宿舍");
			System.out.println("2. 调换宿舍");
			System.out.println("3. 离开宿舍");
			System.out.println("0. 退出");	

			Scanner sc = new Scanner(System.in);
			int op = sc.nextInt();
			switch(op){
				case 1:

					break;
				case 2:
					
					break;
				case 3:

					break;
				case 0:
					System.out.println("成功退出调换宿舍管理!");
					return;
				default:
					System.out.println("您的输入有误, 请重新输入!");
			}
		}
	}
}
