package roglikeRPG;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {

	private ArrayList<Tile> t;
	private Player p;
	private int playerTile;
	public Scanner s;

	public GameManager() {
		t = new ArrayList<Tile>();
		p = new Player();
		playerTile = 0;
		s = new Scanner(System.in);
	}

	public void game() {
		
		loadData();
		
		System.out.println("S's roglike RPG 게임을 시작합니다. 재밋게 즐겨주세요.");

		int select = 0;
		int itemSelect = -1;
		while (p.getHp() > 0 || select != 999) {
			t.add(new Tile());
			// 생성된 방이 빈 방일 경우
			System.out.println("[     현재 플레이어 위치 : " + (int) (playerTile + 1) + "     ]");
			if (t.get(playerTile).i == null && t.get(playerTile).m == null) {
				Boolean isCheck = true;
				while (isCheck) {
					printVoidRoom();
					select = s.nextInt();
					System.out.println("--------------------");
					System.out.println();

					switch (select) {
					case 0:saveData(p);break;// 세이브는 이곳에 작성
					case 1:
						p.getI().showInven();
						break;
					case 2:
						// player안 인벤토리 안의 아이템리스트에 아이템이 하나도 없을 때
						if (p.getI().getI() == null) {
							System.out.println("앗! 아이템이 하나도 없네요. 행운을 빌어요!");
							break;
						}
						p.getI().showInven();
						System.out.println();
						System.out.println("사용할 아이템의 번호를 입력해주세요 : ");
						itemSelect = s.nextInt();

						if (itemSelect < 0 || itemSelect > p.getI().getI().size() - 1) {
							System.out.println("뭔가 잘못선택했나보군요! 다시 시도하세요!");
							break;
						}

						p.getI().getI().get(itemSelect).use(p);// 사용하고 사용한 아이템 삭제
						p.getI().getI().remove(itemSelect);
						break;
					case 3:System.out.println(p);break;
					case 4:
						isCheck = false;
						break;// while탈출
					case 5: p.setHp(p.getHp()+p.getMaxHp()/10);break;
					case 999:
						saveData(p);
						select = 999;
						break;// 세이브 및 게임 완전종료를 여기에 작성
					default:
						System.out.println("뭔가 잘못선택한 모양이에요! 다시 시도하세요!");
					}
					if(select == 999)
					{
						break;
					}
				}
				if(select == 999)
				{
					break;
				}
			}
			// 생성된 방이 아이템 방일 경우
			else if (t.get(playerTile).i != null && t.get(playerTile).m == null) {
				Boolean isCheck = true;
				p.getI().addItem(t.get(playerTile).i);
				while (isCheck) {
					printItemRoom(t.get(playerTile).i);

					select = s.nextInt();
					System.out.println("--------------------");
					System.out.println();

					switch (select) {
					case 0:
						p.getI().showInven();
						break;// 소유중 아이템 보기
					case 1:
						// player안 인벤토리 안의 아이템리스트에 아이템이 하나도 없을 때
						if (p.getI().getI() == null) {
							System.out.println("앗! 아이템이 하나도 없네요. 행운을 빌어요!");
							break;
						}
						p.getI().showInven();
						System.out.println();
						System.out.println("사용할 아이템의 번호를 입력해주세요 : ");
						itemSelect = s.nextInt();

						if (itemSelect < 0 || itemSelect > p.getI().getI().size() - 1) {
							System.out.println("뭔가 잘못선택했나보군요! 다시 시도하세요!");
							break;
						}

						p.getI().getI().get(itemSelect).use(p);// 사용하고 사용한 아이템 삭제
						p.getI().getI().remove(itemSelect);
						break;
					case 2:System.out.println(p);break;
					case 3:
						isCheck = false;
						break;// while탈출
					case 999:
						saveData(p);
						select = 999;
						break;// 세이브 및 게임 완전종료를 여기에 작성
					default:
						System.out.println("뭔가 잘못선택한 모양이에요! 다시 시도하세요!");
					}
					if(select == 999)
					{
						break;
					}
				}
				if(select == 999)
				{
					break;
				}
			}
			// 생성된 방이 몬스터 방일 경우
			else if (t.get(playerTile).i == null && t.get(playerTile).m != null) {
				System.out.println("몬스터방에 들어오셨군요! 전투 개시!!");
				int turnCount = 1;
				Boolean isCheck = true;
				while (isCheck) {
					// 플레이어의 체력이 0과 같거나 0보다 작아진 경우 죽음. 처리 : 저장 없이 게임 종료
					if (p.getHp() <= 0) {
						System.out.println("몬스터와의 전투에서 져버리고 말았어요...");
						System.out.println("다음 생에는 더 잘 살아봐요!");
						System.out.println();
						System.out.println("ps.죽을 경우 세이브되지 않습니다.");
						select = 999;
						break;
					}

					// 몬스터의 체력이 0이되어 플레이어가 이긴 경우. 처리 : 플레이어의 exp에 몬스터의 exp를 더하고 스킵
					if (t.get(playerTile).m.getHp() <= 0) {
						System.out.println();
						System.out.println("--------------------");
						System.out.println("몬스터와의 전투에서 승리했습니다!");
						System.out.println("경험치 획득 EXP + "+ t.get(playerTile).m.getExp());
						System.out.println("--------------------");
						System.out.println();
						p.setExp(p.getExp() + t.get(playerTile).m.getExp());
						checkPlayerLevelup();
						break;
					}

					System.out.println("[ 제  " + turnCount + "턴  ]");
					printMonsterRoom(t.get(playerTile).m);

					select = s.nextInt();
					System.out.println("--------------------");
					System.out.println();

					switch (select) {
					case 0:
						t.get(playerTile).m.setHp(t.get(playerTile).m.getHp() - p.getAtk());
						p.setHp(p.getHp() - t.get(playerTile).m.getAtk());
						break;// 여기서 전투
					case 1:
						// player안 인벤토리 안의 아이템리스트에 아이템이 하나도 없을 때
						if (p.getI().getI() == null) {
							System.out.println("앗! 아이템이 하나도 없네요. 행운을 빌어요!");
							break;
						}
						p.getI().showInven();
						System.out.println();
						System.out.println("사용할 아이템의 번호를 입력해주세요 : ");
						itemSelect = s.nextInt();

						if (itemSelect < 0 || itemSelect > p.getI().getI().size() - 1) {
							System.out.println("뭔가 잘못선택했나보군요! 다시 시도하세요!");
							break;
						}

						p.getI().getI().get(itemSelect).use(p);// 사용하고 사용한 아이템 삭제
						p.getI().getI().remove(itemSelect);
						break;
					case 2: System.out.println(p); break;
					case 3:
						p.setHp(p.getHp() - t.get(playerTile).m.getAtk());
						break;
					default:
						System.out.println("뭔가 잘못선택한 모양이에요! 다시 시도하세요!");
					}
					if(select == 999)
					{
						break;
					}
					turnCount++;
				}
			}
			// 예외 오류
			else {
				System.out.println("gamanager game메소드 오류 발생. tile내부의 조건을 검사할 것.");
			}
			playerTile++;
		}
		System.out.println("게임이 종료됩니다!");
		// 이 while문 안에서 모든 게임 플레이가 이루어짐. 이곳으로 탈출하면 무조건 게임종료.
	}

	public void printVoidRoom() {
		System.out.println();
		System.out.println("--------------------");
		System.out.println("빈 방에 들어오셨군요! 무엇을 하시겠어요?");
		System.out.println();
		System.out.println("[0]세이브");
		System.out.println("[1]아이템 목록 보기");
		System.out.println("[2]아이템 사용");
		System.out.println("[3]플레이어 상세정보");
		System.out.println("[4]아무것도 안하고 스킵");
		System.out.println("[5]휴식(체력회복)");
		System.out.println("[999]게임 종료");
		System.out.println("--------------------");
		System.out.print("선택 : ");
	}

	public void printItemRoom(Item i) {
		System.out.println();
		System.out.println("--------------------");
		System.out.println("아이템방에 들어오셨군요! 축하해요!");
		System.out.println();
		System.out.println("-----획득 아이템 정보-----");
		System.out.println("아이템명 : " + i.getName());
		System.out.println("아이템설명 : " + i.getDesc());
		System.out.println("--------------------");
		System.out.println("[0]아이템 목록 보기");
		System.out.println("[1]아이템 사용");
		System.out.println("[2]플레이어 상세정보");
		System.out.println("[3]아무것도 안하고 스킵");
		System.out.println("[999]게임 종료");
		System.out.println("--------------------");
		System.out.print("선택 : ");
	}

	public void printMonsterRoom(Monster m) {
		System.out.println();
		System.out.println("--------------------");
		System.out.println(m.getName() + "대사 : " + m.getDesc());
		System.out.println();
		System.out.println("-----적 몬스터의 정보-----\t\t\t-----플레이어의 정보-----");
		System.out.println("몬스터 이름 : " + m.getName()+"\t\t\t\t플레이어 HP : "+p.getHp());
		System.out.println("몬스터 체력 : " + m.getHp()+"\t\t\t\t플레이어 LEVEL : "+p.getLevel());
		System.out.println("몬스터 공격력 : " + m.getAtk()+"\t\t\t\t플레이어 ATK : " + p.getAtk());
		System.out.println("몬스터 공략 성공시 획득 경험치 : " + m.getExp()+"\t\t\t플레이어 EXP : "+p.getExp());
		System.out.println("--------------------");
		System.out.println("[0]전투");
		System.out.println("[1]아이템 사용");
		System.out.println("[2]플레이어 상세정보");
		System.out.println("[3]아무것도 안하고 스킵");
		System.out.println("--------------------");
		System.out.print("선택 : ");
	}
	
	public void checkPlayerLevelup() {
		if(p.getExp()>p.getExpmargin()[0]) {
			p.setLevel(2);
			p.levelup();
		}else if(p.getExp()>p.getExpmargin()[1]) {
			p.setLevel(3);
			p.levelup();
		}else if(p.getExp()>p.getExpmargin()[2]) {
			p.setLevel(4);
			p.levelup();
		}else if(p.getExp()>p.getExpmargin()[3]) {
			p.setLevel(5);
			p.levelup();
		}else if(p.getExp()>p.getExpmargin()[4]) {
			p.setLevel(6);
			p.levelup();
		}else if(p.getExp()>p.getExpmargin()[5]) {
			p.setLevel(7);
			p.levelup();
		}else if(p.getExp()>p.getExpmargin()[6]) {
			p.setLevel(8);
			p.levelup();
		}else if(p.getExp()>p.getExpmargin()[7]) {
			p.setLevel(9);
			p.levelup();
		}else if(p.getExp()>p.getExpmargin()[8]) {
			p.setLevel(10);
			p.levelup();
		}else {
			
		}
	}
	
	public void saveData(Player data) {
		File f = new File("data.dat");
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
			oos.flush();
			oos.close();
			System.out.println("플레이어 데이터가 저장되었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadData()
	{
		File x = new File("data.dat");
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(x));
			Player temp = (Player)ois.readObject();
			this.p = temp;
			ois.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("S's roglike RPG 게임에 처음오신걸 환영합니다! 이 문장은 첫 게임 실행시에만 등장합니다!");
			System.out.println("이 게임은 로그 라이크게임으로 처음 하는 분들에게 다소 어려울 수 있습니다.");
			System.out.println("죽을 경우 처음부터 다시시작하게 되며, 죽지않고 중간중간 빈 방에서 저장을 할 수 있습니다! 포션과 레벨업을 통해 캐릭터를 성장시켜보세요!");
			System.out.println("-제작 by irostub-");
			return;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
