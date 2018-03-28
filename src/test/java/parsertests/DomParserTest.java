package parsertests;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import caches.InsureParserCacheManager;
import de.adesso.ais.domainreference.tickets.insure450.Insure450Formel;
import de.adesso.ais.infoservicereference.feldsteuerung.feldelementeigenschaften.impl.StandardFeldelementeigenschaftenStandard;
import de.adesso.ais.infoservicereference.feldsteuerung.feldelementeigenschaften.impl.StandardFeldelementeigenschaftenStandardBearbeitung;
import de.adesso.ais.infoservicereference.feldsteuerung.feldelementeigenschaften.impl.StandardFeldelementeigenschaftenStandardBeauskunftung;
import de.adesso.ais.infoservicereference.feldsteuerung.feldelementeigenschaften.impl.TemplateFeldelementeigenschaftenAusstattungsvariantewaehlen;
import de.adesso.ais.infoservicereference.feldsteuerung.feldelementeigenschaften.impl.TemplateFeldelementeigenschaftenBestellungbeauskunften;
import de.adesso.ais.infoservicereference.feldsteuerung.feldelementeigenschaften.impl.TemplateFeldelementeigenschaftenModellvariantePolo;
import de.adesso.ais.infoservicereference.feldsteuerung.impl.FeldsteuerungBestellsystem;
import de.adesso.ais.infoservicereference.schnittstellensteuerung.impl.SchnittstellensteuerungBestellsystem;
import factory.ParsedPrototypeFactory;
import insure.core.IIdentifiablePrototype;
import insure.core.IPrototypeIdentifier;
import insure.core.impl.Prototype;
import insure.domain.prototype.kontext.IKontext;
import insure.infoservice.feldsteuerung.Feldsteuerung;
import insure.infoservice.feldsteuerung.IEingabeelement;
import insure.infoservice.feldsteuerung.IEingabeelementeigenschaft;
import insure.infoservice.feldsteuerung.IFeldelementeigenschaften;
import insure.infoservice.feldsteuerung.StandardFeldelementeigenschaften;
import insure.infoservice.feldsteuerung.TemplateFeldelementeigenschaften;
import insure.infoservice.feldsteuerung.impl.FeldsteuerungStandard;
import insure.infoservice.schnittstellensteuerung.ISchnittstelle;
import insure.infoservice.schnittstellensteuerung.ISchnittstellenbelieferungsbedingung;
import insure.infoservice.schnittstellensteuerung.ISchnittstellenzuordnung;
import insure.infoservice.schnittstellensteuerung.Schnittstellensteuerung;
import insure.infoservice.schnittstellensteuerung.Schnittstellenzuordnung;
import insure.infoservice.schnittstellensteuerung.impl.SchnittstellensteuerungStandard;
import parser.DomParser;
import tools.JavaPackageNameResolver;

public class DomParserTest {

    InsureParserCacheManager cm = InsureParserCacheManager.INSTANCE;

    static String paths = "/infoservice-reference.insure";
    static DomParser dParser;
    static String[] ecorePaths = { "/domain.ecore", "/infoservice.ecore", "/domain-reference.ecore", "/infoservice-reference.ecore" };

    @BeforeClass
    public static void init() {
        new JavaPackageNameResolver(ecorePaths);
        dParser = new DomParser(paths);
        dParser.parseFileAndReferencedFiles();
    }

    /*--------------------------------------------------------------------------------------------------------
    test if something was parsed and stored in the memory
    ---------------------------------------------------------------------------------------------------------* */
    @Test
    public void domParserWorksCorrectly() {
        Assert.assertNotEquals(dParser.getSpeicher().size(), 0);
        // dParser.printMemoryContentInDetails();
    }

    /*--------------------------------------------------------------------------------------------------------
    StandardFeldelementeigenschaften parsing test
    ---------------------------------------------------------------------------------------------------------* */

    @Test
    public void StandardFeldelementeigebschaftenStandardParsedCorrectly() {
        StandardFeldelementeigenschaftenStandard sf1 = new StandardFeldelementeigenschaftenStandard();
        StandardFeldelementeigenschaften sf2 = (StandardFeldelementeigenschaften) dParser.getSpeicher().get("_SuVeKSbaEeWDoMiQXiXZDQ");
        Assert.assertNotNull(sf2);
        Assert.assertEquals(sf1.getName(), sf2.getName());
        Assert.assertEquals(sf1.getStandardEingabeelementeigenschaft().name(), sf2.getStandardEingabeelementeigenschaft().name());
        Assert.assertEquals(sf1.getStandardSteuerelementeigenschaft().name(), sf2.getStandardSteuerelementeigenschaft().name());

    }

    @Test
    public void StandardFeldelementeigenschaftenStandardBearbeitungParsedCorrectly() {
        StandardFeldelementeigenschaftenStandardBearbeitung sf1 = new StandardFeldelementeigenschaftenStandardBearbeitung();
        StandardFeldelementeigenschaften sf2 = (StandardFeldelementeigenschaften) dParser.getSpeicher().get("_p2lZvqQBEeWbN4B32j-AcQ");
        Assert.assertNotNull(sf2);
        Assert.assertEquals(sf1.getName(), sf2.getName());
        Assert.assertEquals(sf1.getStandardEingabeelementeigenschaft().name(), sf2.getStandardEingabeelementeigenschaft().name());
        Assert.assertEquals(sf1.getStandardSteuerelementeigenschaft().name(), sf2.getStandardSteuerelementeigenschaft().name());

    }

    @Test
    public void StandardFeldelementeigenschaftenStandardBeauskunftungParsedCorrectly() {
        StandardFeldelementeigenschaftenStandardBeauskunftung sf1 = new StandardFeldelementeigenschaftenStandardBeauskunftung();
        StandardFeldelementeigenschaften sf2 = (StandardFeldelementeigenschaften) dParser.getSpeicher().get("_yqcAM6SWEeWkAL6LixI-yg");
        Assert.assertNotNull(sf2);
        Assert.assertEquals(sf1.getName(), sf2.getName());
        Assert.assertEquals(sf1.getStandardEingabeelementeigenschaft().name(), sf2.getStandardEingabeelementeigenschaft().name());
        Assert.assertEquals(sf1.getStandardSteuerelementeigenschaft().name(), sf2.getStandardSteuerelementeigenschaft().name());

    }

    /*--------------------------------------------------------------------------------------------------------
    TemplateFeldelementeigenschaften parsing test
    ---------------------------------------------------------------------------------------------------------* */
    @Test
    public void TemplateFeldelementeigenschaftenAusstattungsvariantewaehlenParsedCorrectly() {
        TemplateFeldelementeigenschaftenAusstattungsvariantewaehlen tpl1 = new TemplateFeldelementeigenschaftenAusstattungsvariantewaehlen();
        TemplateFeldelementeigenschaften tpl2 = null;
        tpl2 = (TemplateFeldelementeigenschaften) dParser.getSpeicher().get("_zt5SyibeEeWDoMiQXiXZDQ");
        Assert.assertNotNull(tpl2);
        Assert.assertEquals(tpl1.getName(), tpl2.getName());
        Assert.assertEquals(tpl1.getEingabeelementeigenschaften().size(), tpl2.getEingabeelementeigenschaften().size());
        for (Entry<IEingabeelement, IEingabeelementeigenschaft> entry : tpl1.getEingabeelementeigenschaften().entrySet()) {
            Assert.assertEquals(tpl1.getEingabeelementeigenschaften().get(entry.getKey()).name(), tpl2.getEingabeelementeigenschaften().get(entry.getKey()).name());
        }
        Assert.assertNotNull(tpl2.getTemplate());
        Assert.assertEquals(((Prototype) tpl2.getTemplate()).getName(), ((Prototype) tpl1.getTemplate()).getName());
    }

    @Test
    public void TemplateFeldelementeigenschaftenBestellungbeauskunftenParsedCorrectly() {
        TemplateFeldelementeigenschaftenBestellungbeauskunften tpl1 = new TemplateFeldelementeigenschaftenBestellungbeauskunften();
        TemplateFeldelementeigenschaften tpl2 = (TemplateFeldelementeigenschaften) dParser.getSpeicher().get("_YQ8un6SXEeWkAL6LixI-yg");
        Assert.assertNotNull(tpl2);
        Assert.assertEquals(tpl1.getName(), tpl2.getName());
        assert (tpl1.getEingabeelementeigenschaften().size() == tpl2.getEingabeelementeigenschaften().size());
        for (Entry<IEingabeelement, IEingabeelementeigenschaft> entry : tpl1.getEingabeelementeigenschaften().entrySet()) {
            Assert.assertEquals(tpl1.getEingabeelementeigenschaften().get(entry.getKey()).name(), tpl2.getEingabeelementeigenschaften().get(entry.getKey()).name());
        }
        Assert.assertNotNull(tpl2.getTemplate());
        Assert.assertEquals(((Prototype) tpl2.getTemplate()).getName(), ((Prototype) tpl1.getTemplate()).getName());
    }

    @Test
    public void TemplateFeldelementeigenschaftenModellvariantePoloParsedCorrectly() {
        TemplateFeldelementeigenschaftenModellvariantePolo tpl1 = new TemplateFeldelementeigenschaftenModellvariantePolo();
        TemplateFeldelementeigenschaften tpl2 = (TemplateFeldelementeigenschaften) dParser.getSpeicher().get("_3zieCbVXEeWaM_Gy2tpIdw");
        Assert.assertNotNull(tpl2);
        Assert.assertEquals(tpl1.getName(), tpl2.getName());
        assert (tpl1.getEingabeelementeigenschaften().size() == tpl2.getEingabeelementeigenschaften().size());
        for (Entry<IEingabeelement, IEingabeelementeigenschaft> entry : tpl1.getEingabeelementeigenschaften().entrySet()) {
            Assert.assertEquals(tpl1.getEingabeelementeigenschaften().get(entry.getKey()).name(), tpl2.getEingabeelementeigenschaften().get(entry.getKey()).name());
        }
        Assert.assertNotNull(tpl2.getTemplate());
        Assert.assertEquals(((Prototype) tpl2.getTemplate()).getName(), ((Prototype) tpl1.getTemplate()).getName());
    }

    /*--------------------------------------------------------------------------------------------------------
    Feldsteuerung parsing test
    ---------------------------------------------------------------------------------------------------------* */
    @Test
    public void FeldsteuerungBestellsystemParsedCorrectly() {

        FeldsteuerungBestellsystem fldst1 = new FeldsteuerungBestellsystem();
        Feldsteuerung fldst2 = (Feldsteuerung) dParser.getSpeicher().get("_KXNjOBsREeWeoYGlWqgNWQ");
        Assert.assertNotNull(fldst2);
        Assert.assertEquals(fldst2.getName(), fldst1.getName());
        Assert.assertEquals(fldst1.getFeldelementeigenschaften().size(), fldst2.getFeldelementeigenschaften().size());
        for (Entry<IKontext, IFeldelementeigenschaften> entry : fldst2.getFeldelementeigenschaften().entrySet()) {
            Assert.assertNotNull(fldst2.getFeldelementeigenschaften().get(entry.getKey()));
            Assert.assertEquals(((Prototype) fldst1.getFeldelementeigenschaften().get(entry.getKey())).getName(), ((Prototype) fldst2.getFeldelementeigenschaften().get(entry.getKey())).getName());
        }
        Assert.assertEquals(fldst1.getIdentifier().name(), (fldst2.getIdentifier().name()));
    }

    @Test
    public void FeldsteuerungStandardParsedCorrectly() {

        FeldsteuerungStandard fldst1 = new FeldsteuerungStandard();
        Feldsteuerung fldst2 = (Feldsteuerung) cm.retrieveFromCache("_FONP99OqEeSVoOolBb6ZYQ");
        Assert.assertNotNull(fldst2);
        Assert.assertEquals(fldst2.getName(), fldst1.getName());
        assert (fldst1.getFeldelementeigenschaften().size() == fldst2.getFeldelementeigenschaften().size());
        for (Entry<IKontext, IFeldelementeigenschaften> entry : fldst1.getFeldelementeigenschaften().entrySet()) {
            Assert.assertEquals(fldst1.getFeldelementeigenschaften().get(entry.getKey()), (fldst2.getFeldelementeigenschaften().get(entry.getKey())));
        }

        Assert.assertEquals(fldst1.getIdentifier().name(), (fldst2.getIdentifier().name()));

    }

    /*--------------------------------------------------------------------------------------------------------
    Schnittstelllensteuerung parsing test
    ---------------------------------------------------------------------------------------------------------* */
    @Test
    public void SchnittstellensteuerungBestellsystemParsedCorrectly() {
        SchnittstellensteuerungBestellsystem shn1 = new SchnittstellensteuerungBestellsystem();
        Schnittstellensteuerung shn2 = (Schnittstellensteuerung) dParser.getSpeicher().get("_uXfMY9OoEeSVoOolBb6ZYQ");

        Assert.assertNotNull(shn2);
        Assert.assertEquals(shn1.getName(), shn2.getName());
        Assert.assertEquals(shn1.getSchnittstellenversorgungen().size(), shn2.getSchnittstellenversorgungen().size());
        Assert.assertEquals(shn1.getBelieferungsbedingungen().size(), shn2.getBelieferungsbedingungen().size());
        for (Map.Entry<IKontext, ISchnittstellenzuordnung> entry : shn1.getSchnittstellenversorgungen().entrySet()) {
            Assert.assertEquals(((Schnittstellenzuordnung) (shn1.getSchnittstellenversorgungen().get(entry.getKey()))).getName(),
                ((Schnittstellenzuordnung) (shn2.getSchnittstellenversorgungen().get(entry.getKey()))).getName());
        }
        Assert.assertEquals(shn1.getIdentifier().name(), shn2.getIdentifier().name());
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void SchnittstellensteuerungStandardParsedCorrectly() {
        SchnittstellensteuerungStandard shn1 = new SchnittstellensteuerungStandard();
        Schnittstellensteuerung shn2 = (Schnittstellensteuerung) cm.retrieveFromCache("_bCozoSbWEeWomoMrx8bFIQ");

        Assert.assertNotNull(shn2);
        Assert.assertEquals(shn1.getName(), shn2.getName());
        Assert.assertEquals(shn1.getSchnittstellenversorgungen().size(), shn2.getSchnittstellenversorgungen().size());
        for (Map.Entry<IKontext, ISchnittstellenzuordnung> entry : shn1.getSchnittstellenversorgungen().entrySet()) {
            Assert.assertEquals(shn1.getSchnittstellenversorgungen().get(entry.getKey()), shn2.getSchnittstellenversorgungen().get(entry.getKey()));
        }
        Assert.assertEquals(shn1.getBelieferungsbedingungen().size(), shn2.getBelieferungsbedingungen().size());
        for (Entry<ISchnittstelle, ISchnittstellenbelieferungsbedingung<?, ?>> entry : shn1.getBelieferungsbedingungen().entrySet()) {
            Assert.assertEquals(shn1.getSchnittstellenversorgungen().get(entry.getKey()), shn2.getSchnittstellenversorgungen().get(entry.getKey()));
        }
        Assert.assertEquals(shn1.getIdentifier(), shn2.getIdentifier());
    }

    /*--------------------------------------------------------------------------------------------------------
    Functions parsing test
    ---------------------------------------------------------------------------------------------------------* */
    @SuppressWarnings("unchecked")
    @Test
    public void Insure450CalculatorEinCalculatorParsedCorrectly() {
        Object calculator = cm.retrieveFromCache("_vgyhc-IYEeaDE84Dco1_hQ");
        Assert.assertNotNull(calculator);
        Insure450Formel calculatorBody = (Insure450Formel) ((DomParser.ParsedContainment) calculator).getContainments().get("_phBZIOLrEeaBppv9oYiIvQ");
        Assert.assertNotNull(calculatorBody);
        Assert.assertNotNull(calculatorBody.getExpression());
        Assert.assertNotNull(calculatorBody.getExpression().getLeft());
        Assert.assertNotNull(calculatorBody.getExpression().getLeft().getLeft());
        Assert.assertNotNull(calculatorBody.getExpression().getLeft().getLeft().getLeft());
        Assert.assertNotNull(calculatorBody.getExpression().getLeft().getLeft().getRight());
        Assert.assertNotNull(calculatorBody.getExpression().getLeft().getLeft());
        Assert.assertNotNull(calculatorBody.getExpression().getRight());
        Assert.assertNotNull(calculatorBody.getExpression().getRight().getLeft());
        Assert.assertNotNull(calculatorBody.getExpression().getRight().getRight());
    }

    /*-------------------------------------------------------------------------------------------------------------
     * identifiable prototypes factory test
     * ------------------------------------------------------------------------------------------------------------*/
    @Test
    public void parsedPrototypeFactoryWorksCorrectly() {
        ParsedPrototypeFactory factory = new ParsedPrototypeFactory(paths);
        Assert.assertNotNull(factory.getIdentifiablePrototypeMap());
        System.out.println(factory.getIdentifiablePrototypeMap().size());
        for (Entry<IPrototypeIdentifier, IIdentifiablePrototype<IPrototypeIdentifier>> entry : factory.getIdentifiablePrototypeMap().entrySet()) {
            System.out.println(entry.getKey() + "    ," + entry.getValue());
        }
    }
}
