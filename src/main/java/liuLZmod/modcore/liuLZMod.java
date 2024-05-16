package liuLZmod.modcore;

import basemod.BaseMod;
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
import liuLZmod.Variable.MyVariable;
import liuLZmod.cards.*;
import liuLZmod.relics.MyRelic;

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
        BaseMod.addDynamicVariable(new MyVariable());
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
        /* 677 */     BaseMod.addAudio(makeID("GUN1"), "ModliuLZ/audio/hermit_gun.ogg");
        /* 678 */     BaseMod.addAudio(makeID("GUN2"), "ModliuLZ/audio/hermit_gun2.ogg");
        /* 679 */     BaseMod.addAudio(makeID("GUN3"), "ModliuLZ/audio/hermit_gun3.ogg");
        /* 680 */     BaseMod.addAudio(makeID("SPIN"), "ModliuLZ/audio/hermit_spin.ogg");
        /* 681 */     BaseMod.addAudio(makeID("RELOAD"), "ModliuLZ/audio/hermit_reload.ogg");
        /*     */   }

}
