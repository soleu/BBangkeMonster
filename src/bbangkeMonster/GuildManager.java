package bbangkeMonster;

import bbangkeMonster.data.PokemonSetting;
import bbangkeMonster.entity.Pokemon;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import static bbangkeMonster.data.PokemonSetting.allPokemon;

public class GuildManager {
    private GuildManager() {
    }

    private static GuildManager guild = new GuildManager();

    public static GuildManager getInstance() {
        return guild;
    }

    PokemonSetting cm = PokemonSetting.getCm();

    Map<String, Integer> nameno = new HashMap<String, Integer>();
    private Vector<Pokemon> guildMember = new Vector<>();

    Random ran = new Random();
    Scanner scan = new Scanner(System.in);

    public Vector<Pokemon> getGuildMember() {
        return guildMember;
    }

    public Vector<Pokemon> getPartyMember() {
        Vector<Pokemon> member = new Vector<>();
        for (Pokemon pokemon : guildMember) {
            if (pokemon.isInParty()) member.add(pokemon);
        }
        return member;
    }

    // 처음 길드 멤버 초기화 (길드 및 파티는 순서대로 4명)
    public void initMember() {
        cm.initPokemon();
        cm.initSuperior();
        for (int i = 0; i < 4; i++) {
            Pokemon pokemon = allPokemon.get(i);
            guildMember.add(pokemon);
            pokemon.setInParty();
            nameno.put(pokemon.getName(), i);
        }
    }

    // 길드 목록 : 현재 길드에 속해있는 멤버 출력
    public void memberList() {
        System.out.println("=== [소유한 전체 포켓몬 목록] ===\n");
        for (int i = 0; i < guildMember.size(); i++) {
            Pokemon pokemon = guildMember.get(i);
            String name = pokemon.getName();
            int level = pokemon.getLevel();
            boolean inparty = pokemon.isInParty();
            System.out.println("[" + (i + 1) + "] 이름 : " + name + " 레벨 : " + level + " 파티 중 : " + inparty + "\n");
        }
    }

    // 길드원 추가 : 기존 캐릭터 매니저에서 랜덤으로 한 명 추가(없는 캐릭터 중에서)
    public void addMember() {
        System.out.println("=== [포켓몬 뽑기] ===");

        boolean run = true;
        while (run) {
            boolean duplicate = false;
            int rNum = ran.nextInt(cm.getAllPokemon().size());

            Pokemon pokemon = cm.getAllPokemon().get(rNum);
            for (int i = 0; i < guildMember.size(); i++) {
                if (pokemon.getName().equals(guildMember.get(i).getName())) {// 기존 길드원과 중복
                    duplicate = true;
                }
            }
            if (!duplicate) {
                guildMember.add(pokemon);
                nameno.put(pokemon.getName(), nameno.size());
                System.out.println("...");
                System.out.println("...");
                System.out.println("[알림] " + pokemon.getName() + "(이)가 추가 되었습니다.\n");
                run = false;
                // userMoney-=1000;
                // 소지금액
            }
        }
    }

    // 길드원 삭제 : 현재 길드에서 선택 삭제(레벨당 골드 지급)
    public void deleteMember() {
        System.out.println("=== [포켓몬 퇴출] ===");
        int num = 0;
        memberList();
        System.out.println("삭제할 포켓몬을 선택하세요 : ");
        num = scan.nextInt();
        num -= 1;
        if (guildMember.get(num).isInParty() == true) {
            System.out.println("현재 소지중인 포켓몬은 삭제할 수 없습니다.");
            return;
        }
        guildMember.remove(num);
        System.out.println("[알림] 해당 포켓몬이 삭제되었습니다.");
    }

    // 파티원 선정(최대 4명)
    public void switchMember() {
        int num1 = 0;
        int num2 = 0;
        while (true) {
            System.out.println("=== [소지 포켓몬 교체] ===");
            memberList();
            System.out.println("휴식을 취할 캐릭터를 선정해주세요 : ");
            num1 = scan.nextInt() - 1;
            if (guildMember.get(num1).isInParty() == false) {
                System.out.println("해당 포켓몬은 이미 휴식중입니다.");
                continue;
            }
            System.out.println("소지할 포켓몬을 선정해주세요 : ");
            num2 = scan.nextInt() - 1;
            if (guildMember.get(num2).isInParty() == true) {
                System.out.println("해당 포켓몬을 이미 소지하고 있습니다.");
                continue;
            }
            break;
        }
        Pokemon inChara = guildMember.get(num1);
        Pokemon outChara = guildMember.get(num2);
        inChara.printCharaInfo();
        System.out.println("이 나가고, ");
        outChara.printCharaInfo();
        System.out.println("이 들어왔습니다.");
        guildMember.get(num1).setOutParty();
        guildMember.get(num2).setInParty();
    }

    // 길드원 no순으로 오름차순 정렬
    public void arrayMember() {
        System.out.println("=== [포켓몬 정렬] ===");
        System.out.println("지정된 순서대로 정렬합니다.");
        for (int i = 0; i < guildMember.size(); i++) {
            String name = cm.getNoname().get(i);
            for (int j = 0; j < guildMember.size(); j++) {
                if (name.equals(guildMember.get(j).getName())) {
                    Pokemon temp = guildMember.get(i);
                    guildMember.set(i, guildMember.get(j));
                    guildMember.set(j, temp);
                }
            }
        }
    }

}