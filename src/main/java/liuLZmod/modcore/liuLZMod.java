package liuLZmod.modcore;

import basemod.BaseMod;
import basemod.abstracts.DynamicVariable;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import liuLZmod.Characters.MyCharacter;
import liuLZmod.Variable.DefaultSecondMagicNumber;
import liuLZmod.cards.*;
import liuLZmod.relics.MyRelic;
import liuLZmod.relics.llz_Els;

import java.nio.charset.StandardCharsets;

import static liuLZmod.Characters.MyCharacter.Enums.MY_CHARACTER;
@SpireInitializer
public class liuLZMod implements EditCardsSubscriber, EditStringsSubscriber , EditCharactersSubscriber ,EditRelicsSubscriber, EditKeywordsSubscriber {
    private static String modID;
    private static final String MY_CHARACTER_BUTTON = "ModliuLZ/img/char/Character_Button.png";
    private static final String MY_CHARACTER_PORTRAIT = "ModliuLZ/img/char/Character_Portrait.png";
    private static final String BG_ATTACK_512 = "ModliuLZ/img/512/bg_attack_512.png";
    private static final String BG_POWER_512 = "ModliuLZ/img/512/bg_power_512.png";
    private static final String BG_SKILL_512 = "ModliuLZ/img/512/bg_skill_512.png";
    private static final String small_orb = "ModliuLZ/img/char/small_orb.png";
    private static final String BG_ATTACK_1024 = "ModliuLZ/img/1024/bg_attack.png";
    private static final String BG_POWER_1024 = "ModliuLZ/img/1024/bg_power.png";
    private static final String BG_SKILL_1024 = "ModliuLZ/img/1024/bg_skill.png";
    private static final String big_orb = "ModliuLZ/img/char/card_orb.png";
    private static final String energy_orb = "ModliuLZ/img/char/cost_orb.png";
    public static final Color MY_COLOR = new Color(134.0F / 255.0F, 137.0F / 255.0F, 220.0F / 255.0F, 1.0F);
    public static final String a_image ="ModliuLZ/img/vfx/gaiz.png";

    public liuLZMod() {
        BaseMod.subscribe(this);
        BaseMod.addColor(MyCharacter.Enums.EXAMPLE_CARD, MY_COLOR, MY_COLOR, MY_COLOR,
                MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, BG_ATTACK_512,
                BG_SKILL_512, BG_POWER_512, energy_orb, BG_ATTACK_1024,
                BG_SKILL_1024, BG_POWER_1024, big_orb, small_orb
        );
        modID = "llz";
    }

    public static void initialize() {new liuLZMod();}

    @Override
    public void receiveEditCards() {
        // 向basemod注册卡牌
        BaseMod.addDynamicVariable((DynamicVariable)new DefaultSecondMagicNumber());
        BaseMod.addCard(new llz_Strike());
        BaseMod.addCard(new llz_Defend());
        BaseMod.addCard(new llz_sike());
        BaseMod.addCard(new llz_zibsz());
        BaseMod.addCard(new llz_popsl());
        BaseMod.addCard(new llz_hej());
        BaseMod.addCard(new llz_lenqj());
        BaseMod.addCard(new llz_leis());
        BaseMod.addCard(new llz_anwz());
        BaseMod.addCard(new llz_jiangz());
        BaseMod.addCard(new llz_cuih());
        BaseMod.addCard(new llz_beny());
        BaseMod.addCard(new llz_lians());
        BaseMod.addCard(new llz_dunj());
        BaseMod.addCard(new llz_qianc());
        BaseMod.addCard(new llz_moryy());
        BaseMod.addCard(new llz_gangthl());
        BaseMod.addCard(new llz_yinqhm());
        BaseMod.addCard(new llz_diedq());
        BaseMod.addCard(new llz_feixv());
        BaseMod.addCard(new llz_guns());
        BaseMod.addCard(new llz_duocdd());
        BaseMod.addCard(new llz_miaossj());
        BaseMod.addCard(new llz_cisb());
        BaseMod.addCard(new llz_hundbc());
        BaseMod.addCard(new llz_jinmyq());
        BaseMod.addCard(new llz_wujcf());
        BaseMod.addCard(new llz_cuanqdj());
        BaseMod.addCard(new llz_zuzbx());
        BaseMod.addCard(new llz_touzcl());
        BaseMod.addCard(new llz_zuangjzq());
        BaseMod.addCard(new llz_cenzbb());
        BaseMod.addCard(new llz_lingbz());
        BaseMod.addCard(new llz_wurpf());
        BaseMod.addCard(new llz_wanq());
        BaseMod.addCard(new llz_diancqj());
        BaseMod.addCard(new llz_jieb());
        BaseMod.addCard(new llz_heiq());
        BaseMod.addCard(new llz_sih());
        BaseMod.addCard(new llz_zansyd());
        BaseMod.addCard(new llz_lus());
        BaseMod.addCard(new llz_zat());
        BaseMod.addCard(new llz_hejj());
        BaseMod.addCard(new llz_congjz());
        BaseMod.addCard(new llz_rongl());
        BaseMod.addCard(new llz_qvxjs());
    }
    // 当开始添加人物时，调用这个方法
    @Override
    public void receiveEditCharacters() {
        // 向basemod注册人物
        BaseMod.addCharacter(new MyCharacter(CardCrawlGame.playerName), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT, MY_CHARACTER);
    }
    @Override
    public void receiveEditRelics() {
        //BaseMod.addRelic(new MyRelic(), RelicType.SHARED); // RelicType表示是所有角色都能拿到的遗物，还是一个角色的独有遗物
        BaseMod.addRelicToCustomPool((AbstractRelic)new MyRelic(), MyCharacter.Enums.EXAMPLE_CARD);
        BaseMod.addRelicToCustomPool((AbstractRelic)new llz_Els(), MyCharacter.Enums.EXAMPLE_CARD);
    }

    public void receiveEditStrings() {
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
            lang = "ENG";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "ModliuLZ/localization/" + lang + "/cards.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "ModliuLZ/localization/" + lang + "/characters.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "ModliuLZ/localization/" + lang + "/relics.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "ModliuLZ/localization/" + lang + "/powers.json");
    }
    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang = "ENG";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        }

        String json = Gdx.files.internal("ModliuLZ/localization/" + lang + "/keywords.json")
                .readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                // 这个id要全小写
                BaseMod.addKeyword("llzmod", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
    public static String getModID() {
        /* 241 */     return modID;
        /*     */   }
    public static String makeID(String idText) {
        /* 672 */     return getModID() + ":" + idText;
        /*     */   }
    public void receiveAddAudio() {
             BaseMod.addAudio(makeID("GUN1"), "ModliuLZ/audio/hermit_gun.ogg");
             BaseMod.addAudio(makeID("GUN2"), "ModliuLZ/audio/hermit_gun2.ogg");
             BaseMod.addAudio(makeID("GUN3"), "ModliuLZ/audio/hermit_gun3.ogg");
             BaseMod.addAudio(makeID("SPIN"), "ModliuLZ/audio/hermit_spin.ogg");
             BaseMod.addAudio(makeID("RELOAD"), "ModliuLZ/audio/hermit_reload.ogg");
             BaseMod.addAudio(makeID("CUIH"), "ModliuLZ/audio/STS_SFX_EnemyAtk_Dagger_v1.ogg");
           }

}
