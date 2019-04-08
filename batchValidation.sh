#!/bin/sh
curl -X POST \
  http://localhost:8080/validateBatch \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
  "filesToProcess": [
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/Faktury_UZSVM_2019_0.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/4_POCET_DAP_DPH_ZA_ROK.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/4_POCET_DAP_DPFO_ZA_ROK.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/4_POCET_DAP_DNE_ZA_ROK.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/4_POCET_DAP_DPPO_ZA_ROK.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/4_POCET_DAP_DSL_ZA_ROK.csv"
    },
    {
      "tabularUrl": "https://data.army.cz/sites/default/files/1.7.2012%20-%2031.3.2019%20zve%C5%99..csv"
    },
    {
      "tabularUrl": "http://www.coi.cz/userdata/files/dokumenty-ke-stazeni/open-data/telefonni-seznam.csv"
    },
    {
      "tabularUrl": "http://www.coi.cz/userdata/files/dokumenty-ke-stazeni/open-data/pracovni-mista.csv"
    },
    {
      "tabularUrl": "http://www.coi.cz/userdata/files/dokumenty-ke-stazeni/open-data/sankce.csv"
    },
    {
      "tabularUrl": "http://www.coi.cz/userdata/files/dokumenty-ke-stazeni/open-data/zakazy.csv"
    },
    {
      "tabularUrl": "http://www.coi.cz/userdata/files/dokumenty-ke-stazeni/open-data/zajisteni.csv"
    },
    {
      "tabularUrl": "http://www.coi.cz/userdata/files/dokumenty-ke-stazeni/open-data/zamereni.csv"
    },
    {
      "tabularUrl": "http://www.coi.cz/userdata/files/dokumenty-ke-stazeni/open-data/kontroly.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/10180/25233177/sldb_zv.csv/344b8550-444d-4af4-80d6-8486755a9d13"
    },
    {
      "tabularUrl": "https://data.gov.cz/soubor/nkod/knihovny.csv"
    },
    {
      "tabularUrl": "https://data.gov.cz/soubor/nkod/sportoviste.csv"
    },
    {
      "tabularUrl": "https://data.gov.cz/soubor/nkod/materske-skoly.csv"
    },
    {
      "tabularUrl": "https://data.gov.cz/soubor/nkod/zakladni-skoly.csv"
    },
    {
      "tabularUrl": "https://data.gov.cz/soubor/nkod/turisticke-cile.csv"
    },
    {
      "tabularUrl": "http://www.msk.cz/opendata/rozpocet.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2015/smlouvy_md_2015.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2015/smlouvy_sfdi_2015.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2015/faktury_md_2015.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2015/faktury_sfdi_2015.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2016/smlouvy_cspsd_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2016/smlouvy_du_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2016/smlouvy_rvc_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2016/smlouvy_szdc_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2016/smlouvy_sps_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2016/smlouvy_uzpln_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2016/faktury_cendis_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2016/faktury_di_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2016/faktury_rlp_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2016/faktury_ucl_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2016/smlouvy_di_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2016/smlouvy_rsd_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2016/smlouvy_sfdi_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2016/faktury_cspsd_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2016/smlouvy_md_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2016/faktury_rsd_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2016/faktury_sfdi_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2016/smlouvy_cendis_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2016/smlouvy_rlp_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2016/smlouvy_ucl_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2016/faktury_md_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2016/faktury_du_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2016/faktury_sps_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2016/faktury_rvc_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2016/faktury_szdc_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2016/faktury_uzpln_2016.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2017/smlouvy_md_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2017/smlouvy_cspsd_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2017/smlouvy_di_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2017/smlouvy_cendis_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2017/smlouvy_du_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2017/smlouvy_rlp_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2017/smlouvy_uzpln_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2017/smlouvy_rvc_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2017/smlouvy_ucl_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2017/smlouvy_rsd_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2017/smlouvy_sps_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2017/smlouvy_szdc_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/smlouvy/2017/smlouvy_sfdi_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2017/faktury_sfdi_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2017/faktury_rvc_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2017/faktury_du_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2017/faktury_cspsd_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2017/faktury_uzpln_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2017/faktury_sps_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2017/faktury_rlp_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2017/faktury_rsd_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2017/faktury_di_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2017/faktury_md_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2017/faktury_ucl_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2017/faktury_szdc_2017.csv"
    },
    {
      "tabularUrl": "http://www.mdcr.cz/MDCR/media/otevrenadata/faktury/2017/faktury_cendis_2017.csv"
    },
    {
      "tabularUrl": "https://mvcr1.opendata.cz/czechpoint/2007.csv"
    },
    {
      "tabularUrl": "https://mvcr1.opendata.cz/czechpoint/2008.csv"
    },
    {
      "tabularUrl": "https://mvcr1.opendata.cz/czechpoint/2009.csv"
    },
    {
      "tabularUrl": "https://portal.isoss.cz/ciselniky/ISoSS_TOC_CIJUR.csv"
    },
    {
      "tabularUrl": "https://portal.isoss.cz/ciselniky/ISoSS_TOC_CIJZK.csv"
    },
    {
      "tabularUrl": "https://portal.isoss.cz/ciselniky/ISoSS_TOC_OBSLU.csv"
    },
    {
      "tabularUrl": "https://portal.isoss.cz/ciselniky/ISoSS_TOC_SJZUR.csv"
    },
    {
      "tabularUrl": "https://portal.isoss.cz/ciselniky/ISoSS_TOC_SLOPR.csv"
    },
    {
      "tabularUrl": "https://portal.isoss.cz/ciselniky/ISoSS_TOC_SLOZN.csv"
    },
    {
      "tabularUrl": "https://portal.isoss.cz/ciselniky/ISoSS_TOC_SLURA.csv"
    },
    {
      "tabularUrl": "https://portal.isoss.cz/ciselniky/ISoSS_TOC_SLOZN.csv"
    },
    {
      "tabularUrl": "https://portal.isoss.cz/ciselniky/ISoSS_TOC_STJZK.csv"
    },
    {
      "tabularUrl": "https://portal.isoss.cz/ciselniky/ISoSS_TOC_STUTA.csv"
    },
    {
      "tabularUrl": "https://portal.isoss.cz/ciselniky/ISoSS_TOC_ZDRZP.csv"
    },
    {
      "tabularUrl": "http://data.nku.cz/download/rozpocet/plneni_zavaznych_ukazatelu.csv"
    },
    {
      "tabularUrl": "http://data.nku.cz/download/podani-obcanu/podani_obcanu.csv"
    },
    {
      "tabularUrl": "http://data.nku.cz/download/kontrolni-akce/koordinovane_audity.csv"
    },
    {
      "tabularUrl": "http://data.nku.cz/download/personalni-oblast/personalni_oblast.csv"
    },
    {
      "tabularUrl": "http://data.nku.cz/download/soucinnost-organu/soucinnost_s_octr.csv"
    },
    {
      "tabularUrl": "http://data.nku.cz/download/kolegium/clenove_kolegia.csv"
    },
    {
      "tabularUrl": "http://data.nku.cz/download/pravni-predpisy/pravni_predpisy_v_kontrolnich_zaverech.csv"
    },
    {
      "tabularUrl": "http://data.nku.cz/download/kontrolovane-osoby/kontrolovane_osoby.csv"
    },
    {
      "tabularUrl": "http://data.nku.cz/download/kontrolni-akce/prehled_kontrolnich_akci.csv"
    },
    {
      "tabularUrl": "http://data.nku.cz/download/rozpocet/rozpocet_2015.csv"
    },
    {
      "tabularUrl": "http://data.nku.cz/download/rozpocet/rozpocet_2017.csv"
    },
    {
      "tabularUrl": "http://data.nku.cz/download/rozpocet/rozpocet_2016.csv"
    },
    {
      "tabularUrl": "http://www.vlada.cz/assets/urad-vlady/otevrena_data/clenove_poradnich_organu/seznam_clenu.csv"
    },
    {
      "tabularUrl": "http://www.vlada.cz/assets/urad-vlady/otevrena_data/seznam_certifikatoru/seznam_certifikatoru.csv"
    },
    {
      "tabularUrl": "http://www.vlada.cz/assets/urad-vlady/otevrena_data/Vypracovane_analyticke_statisticke_a_strategicke_dokumenty/analyticke_dokumenty.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/ukazatele_o_trhu_elektronickych_komunikaci_v_cr-ekonomicke_6.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/ciste-naklady-univerzalni-sluzby_5.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/souhrnny-rozpocet-ctu-2015_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/souhrnny-rozpocet-ctu-2017_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/souhrnny-rozpocet-ctu-2016_1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/velkoobchodni-ceny-za-terminaci-hovoru-ve-verejnych-mobilnich-telefonnich-sitich-cr_1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pocet-zamestnancu-v-pracovnim-a-mimopracovnim-vztahu-k-31-12_7.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/rozhodnuti-o-ulozeni-povinnosti-podniku-smp_1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni_cena_xdsl_dle_operatoru_2015.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni_cena_xdsl_dle_operatoru_2013.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni_cena_xdsl_dle_operatoru_2016.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni_cena_xdsl_dle_operatoru_2014.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni_cena_xdsl_dle_operatoru_2017.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/verejne_telefonni_automaty_v_univerzalni_sluzbe_4.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni-cena-mobilniho-internetu-s-pouzitim-modemu-dle-sluzeb-operatoru-2014.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni-cena-mobilniho-internetu-s-pouzitim-modemu-dle-sluzeb-operatoru-2016.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni-cena-mobilniho-internetu-s-pouzitim-modemu-dle-sluzeb-operatoru-2017.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni-cena-mobilniho-internetu-s-pouzitim-modemu-dle-sluzeb-operatoru-2015.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni-cena-mobilniho-internetu-s-pouzitim-modemu-dle-sluzeb-operatoru-2013.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pokryti-oblasti-cr-infrastrukturou-nga-zsj_2014.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf91.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf54.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf23.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/tabulka3.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf72.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf12.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf73.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf50.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf87.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf19.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf81.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/tabulka6.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf41.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf56.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf75.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf6.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf34.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf53.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf8.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf79.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/tabulka1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf43.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf46.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf3.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf82.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf37.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf64.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf20.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf68.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf16.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf83.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf97.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf71.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf35.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf70.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf93.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/tabulka2.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf39.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf61.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf22.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf25.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf100.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf52.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf69.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/tabulka8.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf36.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf24.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf14.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf45.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf26.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf40.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf58.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf65.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf10.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf59.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf48.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf99.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf86.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf77.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/tabulka4.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf74.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf30.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf89.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf98.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf32.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/tabulka5.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf18.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf49.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf4.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf15.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf28.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf31.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf88.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf9.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf27.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf13.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf21.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf29.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf84.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf96.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf57.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf55.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/tabulka7.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf102.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf94.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf66.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf78.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf60.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf62.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf92.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf44.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf7.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf11.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf38.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf67.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf85.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf2.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf101.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf17.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf47.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf90.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf80.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf33.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf76.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf5.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf42.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf63.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf95_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/graf51.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni_cena_pristupu_k_internetu_upc_2015.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni_cena_pristupu_k_internetu_upc_2016.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni_cena_pristupu_k_internetu_upc_2014_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni-cena-pristupu-k-internetu-upc-2013.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni-cena-pristupu-k-internetu-upc-2017.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pocet-sirokopasmovych-pristupu-k-siti-internet-cr_5.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/service-id-a-pi-kody-pro-digitalni-rozhlasove-programy_1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/prezkum-rozhodnuti-ve-spravnim-soudnictvi_2.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/mezinarodni-srovnani-mtr-2017.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/mezinarodni-srovnani-mtr-2016_2.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/mezinarodni-srovnani-mtr-2014_1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/mezinarodni-srovnani-mtr-2015.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/mezinarodni-srovnani-mtr-2013_2.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/objem-prepravenych-postovnych-zasilek_2.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/imports/import_rozhlas/prehled_rozhlasovych_kmitoctu.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/ekonomicke-ukazatele-trhu-postovnich-sluzeb_4.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/platy-clenu-rady-ctu-2015.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/platy-clenu-rady-ctu-2014_4.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/platy-clenu-rady-ctu-2016.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/platy-clenu-rady-ctu-2017.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/platy-clenu-rady-ctu-2012_4.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/platy-clenu-rady-ctu-2013_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/ztrata-z-univerzalni-sluzby-2001-az-2006.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/rozhodnuti-o-ulozeni-povinnosti-v-ramci-univerzalni-sluzby_1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/regulovane_maximalni_ceny_3.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/zakladni-sluzby-poskytnute-drzitelem-postovni-licence_4.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/ceny_dle_operatoru_k_20170615.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/ceny_dle_operatoru_k_20140701.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/ceny_dle_operatoru_k_20160701.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/ceny_dle_operatoru_k_20130701.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/ceny_dle_operatoru_k_20120701.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/ceny_dle_operatoru_k_20150701.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pocet-osob-na-materske-rodicovske-dovolene-k-31-12_9.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pocet-zamestnancu-v-oboru-e-komunikaci_3.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/koridor_4_decin-praha-c.budejovice-h.dvoriste_1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/koridor_2_breclav-zilina-c.trebova_1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/koridor_1_decin-praha-breclav_1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/koridor_3_cheb-zilina_1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pocet-provozovatelu-opravnenych-nabizet-konkretni-postovni-sluzbu-2014_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pocet-provozovatelu-opravnenych-nabizet-konkretni-postovni-sluzbu-2016.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pocet-provozovatelu-opravnenych-nabizet-konkretni-postovni-sluzbu-2015.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pocet-provozovatelu-opravnenych-nabizet-konkretni-postovni-sluzbu-2013.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/ukazatele-o-trhu-rozhlasoveho-a-tv-vysilani_3.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/imports/import_radiove_kmitocty/import_radiove_kmitocty_opravneni.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/mobilni-prumerna-cena-za-minutu_1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/mobilni-internet_2.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/mobilni-cena-podle-kosu_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/barometr-oecd.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/mobilni-cena-podle-kosu-2006-az-2013.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/barometr-pevny-internet_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/prostredky-na-platy-zamestnancu_7.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pridely-radiovych-kmitoctu-cr_4.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/vyvoj-mesicnich-a-jednorazovych-cen-ppv-spv_2.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pocet-zamestnancu-podle-veku-a-pohlavi-k-31-12_6.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/vydaje-na-zahranicni-pracovni-cesty-2014_4.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/vydaje-na-zahranicni-pracovni-cesty-2012_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/vydaje-na-zahranicni-pracovni-cesty-2013_1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/ostatni-platby-za-provedenou-praci_4.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/stiznosti-podle-zek-2013.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/stiznosti-podle-zek-2011.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/stiznosti-podle-zek-2016_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/stiznosti-podle-zek-2012_1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/stiznosti-podle-zek-2017.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/stiznosti-podle-zek-2010.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/stiznosti-podle-zek-2015_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/stiznosti-podle-zek-2014.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/cleneni-zamestnancu-dle-vzdelani-a-pohlavi-k-31-12_6.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/rozhodnuti-o-stanoveni-smp_1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni-ceny-za-zrizeni-pevne-linky-telefonica_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/prehled-o-stavu-radiokomunikacniho-uctu_3.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/prehled-polozek.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/imports/import_televize/prehled_opravneni_televizni_vysilace.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/statistika-stiznosti-na-cinnost-ctu-2015.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/statistika-stiznosti-na-cinnost-ctu-2010.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/statistika-stiznosti-na-cinnost-ctu-2017.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/statistika-stiznosti-na-cinnost-ctu-2012.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/statistika-stiznosti-na-cinnost-ctu-2016.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/statistika-stiznosti-na-cinnost-ctu-2011.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/statistika-stiznosti-na-cinnost-ctu-2014.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/statistika-stiznosti-na-cinnost-ctu-2013.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/vydaje-na-vzdelavani-zamestnancu-k-31-12_12.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/prumerny-hruby-mesicni-plat-k-31-12_6.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/prehled-o-stavu-uctu-pro-rozvoj-zemskeho-digitalniho-televizniho-vysilani_1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pokryti-oblasti-cr-infrastrukturou-nga-krajezsjbezdom.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/imports/import_anteny_diagramy/bc_ant_diagrams.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/ztrata-z-poskytovani-univerzalni-sluzby-zvlastni-ceny_14.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pocet-zamestnancu-dle-pohlavi-a-jejich-fluktuace-k-31-12_4.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/spory-dle-129-zek-2015.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/spory-dle-129-zek-2010.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/spory-dle-129-zek-2014_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/spory-dle-129-zek-2013_1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/spory-dle-129-zek-2011_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/spory-dle-129-zek-2017.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/spory-dle-129-zek-2012_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/spory-dle-129-zek-2016.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/imports/import_provozovatele/evidence_provozovatelu.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/imports/import_podnikatele/podnikatele_v_elektronickych_komunikaci.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/imports/import_bod_bod/prehled_pevnych_radiovych_systemu_bod_bod.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pokryti-oblasti-cr-infrastrukturou-nga-kraje-pripojky-2014.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/evidence-zarizeni-pevne-sluzby_1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/srovnani_tarifu_dle_typu_spotrebitele_2014.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/srovnani_tarifu_dle_typu_spotrebitele_2015.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/srovnani_tarifu_dle_typu_spotrebitele_2013.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pocet-prenesenych-cisel-v-mobilnich-a-pevnych-sitich_9.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/plneni-povinneho-podilu-osob-se-zdravotnim-postizenim-2013_2.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/plneni-povinneho-podilu-osob-se-zdravotnim-postizenim-2012.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/plneni-povinneho-podilu-osob-se-zdravotnim-postizenim-2014_1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/plneni-povinneho-podilu-osob-se-zdravotnim-postizenim-2017.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/plneni-povinneho-podilu-osob-se-zdravotnim-postizenim-2015.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/plneni-povinneho-podilu-osob-se-zdravotnim-postizenim-2016.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni_cena_xdsl_telefonica_2017.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni_cena_xdsl_telefonica_2015_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni_cena_xdsl_telefonica_2014_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni_cena_xdsl_telefonica_2016.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni_cena_xdsl_telefonica_2013_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pokryti-dalnic-mobilnim-signalem-d10.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pokryti-dalnic-mobilnim-signalem-d3.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pokryti-dalnic-mobilnim-signalem-d2.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pokryti-dalnic-mobilnim-signalem-d0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pokryti-dalnic-mobilnim-signalem-d52.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pokryti-dalnic-mobilnim-signalem-d11.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pokryti-dalnic-mobilnim-signalem-d7.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pokryti-dalnic-mobilnim-signalem-d55.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pokryti-dalnic-mobilnim-signalem-d5.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pokryti-dalnic-mobilnim-signalem-d6.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pokryti-dalnic-mobilnim-signalem-d46.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pokryti-dalnic-mobilnim-signalem-d4.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pokryti-dalnic-mobilnim-signalem-d1_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pokryti-dalnic-mobilnim-signalem-d35.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pokryti-dalnic-mobilnim-signalem-d8.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/imports/import_bmis/evidence_stanic_bmis.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/mapovani-kvality-poskytovanych-sluzeb-2016_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/mapovani-kvality-poskytovanych-sluzeb-2017.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/mapovani-kvality-poskytovanych-sluzeb-2013_13.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/velkoobchodni-ceny-xdsl-s-pristupem-k-vts-2014.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/velkoobchodni-ceny-xdsl-s-pristupem-k-vts-2015_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/velkoobchodni-ceny-xdsl-s-pristupem-k-vts-2013.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/postovni-sit-obsluzna-mista-drzitele-postovni-licence_3.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/imports/import_kontroly/Kontroly_a_pravomocne_pokuty.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/faktury_uhrazene_v_roce_2017_kap328.csv.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/faktury_uhrazene_v_roce_2016_kap328_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/faktury_uhrazene_v_roce_2015_kap328_7.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni-cena-internetu-v-mobilu-dle-sluzeb-operatoru-2017.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni-cena-internetu-v-mobilu-dle-sluzeb-operatoru-2014_1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni-cena-internetu-v-mobilu-dle-sluzeb-operatoru-2013.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni-cena-internetu-v-mobilu-dle-sluzeb-operatoru-2015.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni-cena-internetu-v-mobilu-dle-sluzeb-operatoru-2016.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/schvalena-zarizeni.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/imports/import_anteny/evidence-anten-pevne-sluzby.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/maloobchodni-cena-za-pouzivani-pevne-linky-bytove_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/imports/import_numbers/pridelena_cisla_a_kody.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/ciste-naklady-na-poskytovani-zakladnich-sluzeb_1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/pocet-zamestnancu-v-postovnich-sluzbach_2.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/velkoobchodni-ceny-xdsl-bez-pristupu-k-vts-2014_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/velkoobchodni-ceny-xdsl-bez-pristupu-k-vts-2013_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/velkoobchodni-ceny-xdsl-bez-pristupu-k-vts-2015.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/velkoobchodni-ceny-xdsl-bez-pristupu-k-vts-2016.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/imports/import_kmitoctova_pasma/kmitoctova_pasma.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/rozhodnuti-o-cene_2.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/poplatky-za-vyuzivani-radiovych-kmitoctu_4.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/imports/import_radiove_kmitocty/import_radiove_kmitocty_letecka_opravneni.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/imports/import_radiove_kmitocty/import_radiove_kmitocty_individualni_opravneni.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/duvody-uzavreni-povinnych-post_2.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/ukazatele_o_trhu_elektronickych_komunikaci_v_cr-pevna_4.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/ceny-vybranych-vnitrostatnich-a-zahranicnich-zakladnich-sluzeb-k-31-12_4.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/poskytovani-infomaci-106_2.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/prehled-o-stavu-uctu-cizich-prostredku-k-uhrade-nakladu-poskytovateli-univerzalni-sluzby_4.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/stiznosti-dle-zps_2.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/overovani-odborne-zpusobilosti-2016_0.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/overovani-odborne-zpusobilosti-2014.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/overovani-odborne-zpusobilosti-2015.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/overovani-odborne-zpusobilosti-2017.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/ukazatele_o_trhu_elektronickych_komunikaci_v_cr-mobil_8.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/oznamena-zarizeni_1.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/imports/import_ruseni/setreni_ruseni.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/service-id-konkretnich-sluzeb_11.csv"
    },
    {
      "tabularUrl": "http://data.ctu.cz/sites/default/files/imports/import_radiove_kmitocty/import_radiove_kmitocty_namorni_opravneni.csv"
    },
    {
      "tabularUrl": "http://wwwinfo.mfcr.cz/ares/ares_opendata.html.cz"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/2_DPH_V_TISICICH_KC_ZA_ROK.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/2_DPPO_V_TISICICH_KC_ZA_ROK.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/2_DANOVA_POVINNOST_V_MILIONECH_KC_ZA_ROK.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/2_POCTY_DAP.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/2_DSL_V_TISICICH_KC_ZA_ROK.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/2_INKASA_V_MILIONECH_KC_ZA_ROK.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/2_DPPO_NACE_V_TISICICH_KC_ZA_ROK.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/2_DNE_V_TISICICH_KC_ZA_ROK.csv"
    },
    {
      "tabularUrl": "http://monitor.statnipokladna.cz/2017/zdrojova-data/transakcni-data"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/Uhrazene_faktury_Kapitola_312_01-12-2016_0.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/2018-11.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/Seznam-partneru_MF.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/Uhrazene-faktury_2010_0.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/Uhrazene_faktury_Kapitola_312_1-1-2016_1.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/Uhrazene_faktury_Fond_Privatizace_2017_0.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/Uhrazene_faktury_Kapitola_312_2017_1.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/Uhrazene_faktury_Fond_Privatizace_2018_0_0.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/Uhrazene_faktury_Fond_Privatizace_01-12-2016_0.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/Uhrazene-faktury_2014_0.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/Uhrazene-faktury_2013_0.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/Uhrazene-faktury_2011_0.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/Uhrazene_faktury_Fond_Privatizace_1-10-2015_0.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/Uhrazene-faktury_2012_0.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/faktury_UZSVM_2018_6.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/faktury_UZSVM_2017_0.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/Vysledky_PH_za_rok_2015.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/Prehled_poruseni_2013.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/Vysledky_PH_2014.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/Platne_neplatne_smlouvy_2017-11.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/Casove-rady-vyvoje-statnich-zaruk_UTF.csv"
    },
    {
      "tabularUrl": "http://data.mfcr.cz/sites/default/files/8_DS_V_DAP_ODCITATELNA_POLOZKA_HODNOTA_DARU_V_MILIONECH_ZA_ROK.csv"
    },
    {
      "tabularUrl": "http://www.crr.cz/Files/Docs/OBJ_2019.csv"
    },
    {
      "tabularUrl": "http://www.uur.cz/images/7-ustav-uzemniho-rozvoje/otevrena-data-uur/objednavky-2019.csv"
    },
    {
      "tabularUrl": "http://www.crr.cz/Files/Docs/FA_2019.csv"
    },
    {
      "tabularUrl": "http://www.crr.cz/Files/Docs/FA_2016.csv"
    },
    {
      "tabularUrl": "http://www.crr.cz/Files/Docs/FA_2017.csv"
    },
    {
      "tabularUrl": "http://www.crr.cz/Files/Docs/FA_2018.csv"
    },
    {
      "tabularUrl": "http://www.crr.cz/Files/Docs/OBJ_2016.csv"
    },
    {
      "tabularUrl": "http://www.crr.cz/Files/Docs/OBJ_2017.csv"
    },
    {
      "tabularUrl": "http://www.crr.cz/Files/Docs/OBJ_2018.csv"
    },
    {
      "tabularUrl": "http://www.crr.cz/Files/Docs/SML_2019.csv"
    },
    {
      "tabularUrl": "http://www.crr.cz/Files/Docs/SML_2016.csv"
    },
    {
      "tabularUrl": "http://www.crr.cz/Files/Docs/SML_2017.csv"
    },
    {
      "tabularUrl": "http://www.crr.cz/Files/Docs/SML_2018.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/28df9589-049d-4429-b887-293452caa290/resource/79eb763c-3a0a-417c-89ce-a99e9a83b637/download/crr.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/466856df-a18a-49f0-8ff9-e6588a2c17ad/resource/29d609c4-863b-4fb8-926e-0d3b06179839/download/czt.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/ddc1b1ce-4f40-43c1-8b2d-8ae960a7b9e3/resource/7ea817e4-d3ba-41f0-9d86-0bab9bc57d08/download/kapitola317.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/a6643bff-e2b5-4a5f-8372-70ad260fc224/resource/8d538827-38a1-4364-b82d-6f50e86b07b6/download/uur.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/5e863952-41f6-4fc4-bcec-af63c40f5726/resource/693f6edc-5413-4ef8-aaaa-ad536f25b9b3/download/cusersvanlukonedrive-mmrstaene-souboryzavazne-ukazatele-mmr-2018-2020.csv"
    },
    {
      "tabularUrl": "http://www.czechtourism.cz/getmedia/36f7347e-4bb1-4b2f-a0cf-8e3f9c9d8083/CzT_faktury_4.csv.aspx"
    },
    {
      "tabularUrl": "http://www.czechtourism.cz/getmedia/b06e9b04-0128-4f97-8195-b4ca0990c565/CzT_faktury_2017_10.csv.aspx"
    },
    {
      "tabularUrl": "https://www.czechtourism.cz/getmedia/7809aee4-a221-4774-bd84-0a44da419570/CzT_faktury_2018.csv.aspx"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/012d9e10-3779-484f-a8f9-ce8445d02141/resource/19691295-09e0-4f2b-8d10-3e36e023d873/download/cusersvanlukonedrive-mmrstaene-souboryfaktury_2016.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/89717919-5db4-469d-842c-01d4f54b0ed9/resource/922a41d9-9bf2-4ba6-a9b3-0ab48a197077/download/cusersvanlukonedrive-mmrstaene-souboryfaktury_2017.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/bb0304c5-b88c-4408-a851-a4cd0ff50b41/resource/9b78198a-397b-4276-bb9e-67104fda114d/download/jsdilenedata-pro-katalogoufs___k-uveejnnifaktury_2019_02-20190311.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/5f2d1e58-f7e5-4c00-af9c-2cdfe9a4965f/resource/0909932c-f6fc-4ca7-9126-f8c79ce0eecc/download/jsdilenedata-pro-katalogoufs___k-uveejnnifaktury_2018-12_20190101.csv"
    },
    {
      "tabularUrl": "http://www.sfrb.cz/fileadmin/user_upload/Faktury_2019.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/a18ec18b-5f24-465a-bc7a-292f5629ea73/resource/1cc0fbf0-b851-43f9-a46e-97ef03d8ff78/download/seznam_faktur_do_13_12_2016.csv"
    },
    {
      "tabularUrl": "http://www.sfrb.cz/fileadmin/user_upload/Seznam_faktur_2017.csv"
    },
    {
      "tabularUrl": "http://www.sfrb.cz/fileadmin/user_upload/Seznam_faktur_2018.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/cc7ef807-36a7-4058-886f-e352d6979026/resource/537dd862-5b5e-4068-8ffb-a4f2270de46b/download/cusersvanlukonedrive-mmrstaene-souboryfaktury_2015.csv"
    },
    {
      "tabularUrl": "http://www.sfrb.cz/fileadmin/user_upload/Objednavky_2019.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/d5b7583a-7239-4b1d-a496-a648e1f5ab30/resource/987850d2-4c0e-4469-a6cb-352d11c9cbb7/download/objednavky_do_13_12_2016.csv"
    },
    {
      "tabularUrl": "http://www.sfrb.cz/fileadmin/user_upload/Objednavky_2017.csv"
    },
    {
      "tabularUrl": "http://www.sfrb.cz/fileadmin/user_upload/Objednavky_2018.csv"
    },
    {
      "tabularUrl": "http://www.uur.cz/images/7-ustav-uzemniho-rozvoje/otevrena-data-uur/objednavky-2015.csv"
    },
    {
      "tabularUrl": "http://www.uur.cz/images/7-ustav-uzemniho-rozvoje/otevrena-data-uur/objednavky-2016.csv"
    },
    {
      "tabularUrl": "http://www.uur.cz/images/7-ustav-uzemniho-rozvoje/otevrena-data-uur/objednavky-2017.csv"
    },
    {
      "tabularUrl": "http://www.uur.cz/images/7-ustav-uzemniho-rozvoje/otevrena-data-uur/objednavky-2018.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/f7bcd268-af84-4926-82b7-41f39f7410d3/resource/44319910-eb91-46db-ac90-55c9880b4d02/download/objednavky_2015.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/f4a910dd-3691-4fdc-b25d-12e715151037/resource/91cc7758-0f7b-4f66-9e37-4cc44b730647/download/objednavky_2016_2017_1.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/b9804156-9226-4973-9dff-fae45f45b96d/resource/a47ce911-84a0-44b8-80a4-554d82688627/download/objednavky_2017_0.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/4e288cc4-bcfa-4e09-b19c-d71ec002ee8f/resource/3a42bffb-3bb7-45dd-914b-cf7dfd7a0c5d/download/poradci-i.-pololeti-2016.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/0d69a83d-cbd5-4eef-8dd4-a065e068531f/resource/f66bb26d-3729-42e2-97c4-12ac5d39e602/download/poradci-i.-pololeti-2017.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/6f178ea8-3494-4b01-a4b5-1159e5ea4cb0/resource/9de4935e-8c39-4471-9cc7-41a0d9ac1b24/download/poradci-ii.-pololeti-2016.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/ebb27879-1b85-4a51-a57d-b951835a83b9/resource/1a198b17-8615-42d2-a362-879dbbd4a8fc/download/jdata-pro-katalogokoporadci-ii.-pololeti-2017.csv"
    },
    {
      "tabularUrl": "http://www.czechtourism.cz/getmedia/c9a5aa64-0dd4-4566-8018-739e7fb67b6e/CzT_smlouvy_objednavky_4.csv.aspx"
    },
    {
      "tabularUrl": "http://www.czechtourism.cz/getmedia/deffc4b8-1b05-4ebf-8b91-4f530f9e5cd6/CzT_smlouvy_objednavky_2017_10.csv.aspx"
    },
    {
      "tabularUrl": "https://www.czechtourism.cz/getmedia/0b9686d7-4084-4709-9d32-b91685689eb3/CzT_smlouvy_objednavky_2018.csv.aspx"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/8b1276a5-8160-44b3-810a-1281c4d267aa/resource/26e501e6-d87d-48f1-b30f-9bf0fa52fa08/download/mmr_smlouvy_objednavky_2017.csv"
    },
    {
      "tabularUrl": "http://www.sfrb.cz/fileadmin/user_upload/Smlouvy_2019.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/c3b8b471-3e77-4981-9ad9-42d9cfc12b3e/resource/38adc096-9d3c-427d-bf24-97754d6a87ff/download/seznam_smluv_do_13_12_2016.csv"
    },
    {
      "tabularUrl": "http://www.sfrb.cz/fileadmin/user_upload/Seznam_smluv_2017.csv"
    },
    {
      "tabularUrl": "http://www.sfrb.cz/fileadmin/user_upload/Seznam_smluv_2018.csv"
    },
    {
      "tabularUrl": "http://www.uur.cz/images/7-ustav-uzemniho-rozvoje/otevrena-data-uur/smlouvy-2015.csv"
    },
    {
      "tabularUrl": "http://www.uur.cz/images/7-ustav-uzemniho-rozvoje/otevrena-data-uur/smlouvy-2016.csv"
    },
    {
      "tabularUrl": "http://www.uur.cz/images/7-ustav-uzemniho-rozvoje/otevrena-data-uur/smlouvy-2017.csv"
    },
    {
      "tabularUrl": "http://www.uur.cz/images/7-ustav-uzemniho-rozvoje/otevrena-data-uur/smlouvy-2018.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/4e48de6c-b876-4c95-b096-ab2fd7b610cc/resource/64cdea10-727e-4cff-a4df-3d65ef63884c/download/smlouvy_2015.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/84faeeaf-ab1b-401e-af4f-5bce498e3004/resource/9250a9e4-fbb7-4cdd-8109-85e6e993c1df/download/smlouvy_2016_6.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/15cd2908-3694-4e81-8d06-28fd57e730ef/resource/e2e2c927-a688-4a69-9120-0aecb917926a/download/smlouvy_2017_0.csv"
    },
    {
      "tabularUrl": "http://www.uur.cz/images/7-ustav-uzemniho-rozvoje/otevrena-data-uur/faktury-2015.csv"
    },
    {
      "tabularUrl": "http://www.uur.cz/images/7-ustav-uzemniho-rozvoje/otevrena-data-uur/faktury-2016.csv"
    },
    {
      "tabularUrl": "http://www.uur.cz/images/7-ustav-uzemniho-rozvoje/otevrena-data-uur/faktury-2017.csv"
    },
    {
      "tabularUrl": "http://www.uur.cz/images/7-ustav-uzemniho-rozvoje/otevrena-data-uur/faktury-2018.csv"
    },
    {
      "tabularUrl": "http://www.uur.cz/images/7-ustav-uzemniho-rozvoje/otevrena-data-uur/faktury-2019.csv"
    },
    {
      "tabularUrl": "http://www.uur.cz/images/7-ustav-uzemniho-rozvoje/otevrena-data-uur/smlouvy-2019.csv"
    },
    {
      "tabularUrl": "http://data.mmr.cz/dataset/047470bb-1bd5-474f-b328-5a91b587a9e5/resource/58d75a12-34e9-4e5b-ac40-967b793f9bc9/download/cusersvanlukonedrive-mmrstaene-souboryzavazne-ukazatele-mmr-2019-2021.csv"
    },
    {
      "tabularUrl": "https://analytika.kr-vysocina.cz/ReportServer_SQLSERVERPUBLIC/Pages/ReportViewer.aspx?%2fEK%2fEK_klikaci_opendata&rs:Command=Render&rs:Format=excel"
    },
    {
      "tabularUrl": "http://www.mzp.cz/opendata/aopk-faktury_2015.csv"
    },
    {
      "tabularUrl": "http://www.mzp.cz/opendata/aopk-faktury_2016.csv"
    },
    {
      "tabularUrl": "http://www.mzp.cz/opendata/cenia-faktury_2018.csv"
    },
    {
      "tabularUrl": "http://www.mzp.cz/opendata/cgs-faktury_2017.csv"
    },
    {
      "tabularUrl": "http://www.mzp.cz/opendata/cgs-faktury_2015-2016.csv"
    },
    {
      "tabularUrl": "http://www.mzp.cz/opendata/mzp-faktury_2016.csv"
    },
    {
      "tabularUrl": "http://www.mzp.cz/www/smlouvy-web.nsf/exportAllAsCSV.xsp?id=i"
    },
    {
      "tabularUrl": "http://www.mzp.cz/opendata/sjcr-faktury_2017.csv"
    },
    {
      "tabularUrl": "http://www.mzp.cz/opendata/sjcr-faktury_2015-2016.csv"
    },
    {
      "tabularUrl": "https://opendata.nppodyji.cz/faktury.php?export=csv"
    },
    {
      "tabularUrl": "http://www.mzp.cz/opendata/GMO.csv"
    },
    {
      "tabularUrl": "http://www.mzp.cz/opendata/aopk-objednavky_2016.csv"
    },
    {
      "tabularUrl": "http://www.mzp.cz/opendata/cgs-objednavky_2017.csv"
    },
    {
      "tabularUrl": "http://www.mzp.cz/opendata/mzp-objednavky_2016.csv"
    },
    {
      "tabularUrl": "http://www.mzp.cz/www/smlouvy-web.nsf/exportAllAsCSV.xsp?id=ov"
    },
    {
      "tabularUrl": "http://www.mzp.cz/opendata/aopk-smlouvy_2016.csv"
    },
    {
      "tabularUrl": "http://www.mzp.cz/opendata/cgs-smlouvy_2017.csv"
    },
    {
      "tabularUrl": "http://www.mzp.cz/opendata/sjcr-smlouvy_2017.csv"
    },
    {
      "tabularUrl": "https://opendata.nppodyji.cz/smlouvy.php?export=csv"
    },
    {
      "tabularUrl": "http://www.mzp.cz/www/smlouvy-web.nsf/exportAllAsCSV.xsp?id=cv"
    },
    {
      "tabularUrl": "http://www.mzp.cz/opendata/CITES_AnnualReport_2015.csv"
    },
    {
      "tabularUrl": "http://www.mzp.cz/opendata/CITES_AnnualReport_2016.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/e5740d0f-d683-490a-a14c-9dc7fffd3abb/resource/968026b1-0f7c-4471-aa7c-a244574f2030/download/959c0e6f-5afb-489f-95ef-c9c2982963de-adopcezvirata.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/dacce95a-e6e0-45fd-8203-9dbaa15b140c/resource/c6871f74-f911-437d-a01b-d963feccfe29/download/9d4c299f-e2af-4b9e-80d9-7f19199e6333-temata-rozhovoru.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/dacce95a-e6e0-45fd-8203-9dbaa15b140c/resource/b06eb0c0-b3b9-48bf-82d0-b05e51092794/download/b4bb8449-0c9f-4e58-be7c-bd03f06d10e2-poet-konverzaci-za-obdobi.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/dacce95a-e6e0-45fd-8203-9dbaa15b140c/resource/6dbb5deb-982e-4c8e-b6da-9fac9514e98b/download/8cff5780-6005-4b51-ae49-42d5cbef802f-poet-konverzaci-po-hodinach.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/dacce95a-e6e0-45fd-8203-9dbaa15b140c/resource/14609d3e-75bb-44b5-8967-0333b0d05f10/download/172de456-51c9-457e-8ba0-e40c3c37917c-prmrny-poet-konverzaci-po-dnech-v-tydnu.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/e40f4f4a-35e4-4d77-ab2a-407bc3068f3e/resource/9e951681-2b30-40cd-b024-929d683158b4/download/eacd2ebe-ee62-4472-bda7-0905aa77f70a-eacd2ebe-ee62-4472-bda7-0905aa77f70a-lookup_table-utf8.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/e40f4f4a-35e4-4d77-ab2a-407bc3068f3e/resource/43b840c4-b27b-491f-9362-66cbb8336b93/download/87b7008e-bce4-4a19-a6c4-ff34edecb12f-cykloscitace.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/fd64f064-f4f0-43e4-8ea2-c185dcb4a25b/resource/a740fa79-3d99-499e-9f2f-fae99e8d1a35/download/e67ffdd7-7d6d-4e25-a44e-78d477112fe8-dlouhodoby-hmotny-neodepisovany-t.-03-31-no-price-gordi.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/fd64f064-f4f0-43e4-8ea2-c185dcb4a25b/resource/4402472a-8070-4fd3-b232-b3c0e28e42e1/download/36eb2359-4f50-41fd-a9eb-bf48e5145ab9-11.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/0e7c5656-58de-4e5b-aba4-20686193c35c/resource/35cf24a2-2c2c-41a1-a395-f43d3d2d23d3/download/6d387f4d-dacb-4519-b3cb-252877cdaba5-22-29maj-list-1.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/0e7c5656-58de-4e5b-aba4-20686193c35c/resource/d042474e-0a9d-4da1-8519-e489d1bb5f52/download/90ceb5d3-5484-4940-832b-ef205e10e533-12.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_11_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_10_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_11_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_12_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_03_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_12_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_06_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_06_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_10_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_07_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_04_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2019_01_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_01_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_07_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_04_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_01_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_08_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_03_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_09_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_05_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2019_01_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_05_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_12_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_04_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_05_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_03_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_03_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_02_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_08_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_10_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_01_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_01_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_11_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_07_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_02_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_12_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_04_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_07_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_06_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_09_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_11_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_04_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2019_01_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_06_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_01_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_11_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_06_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_01_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_08_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_04_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_11_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_09_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_12_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_05_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_07_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_09_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_07_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_12_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_09_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_05_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_11_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_04_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_12_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_06_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_11_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_10_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_08_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_08_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_06_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_08_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_05_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_09_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_02_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_10_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_02_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_11_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_08_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_04_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_03_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_10_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_09_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_08_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_12_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_09_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_11_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_06_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_10_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_04_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_11_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_03_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_09_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_01_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_02_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_08_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_10_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_01_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_04_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_10_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_09_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_02_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_10_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_12_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_02_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_09_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_07_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_08_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_10_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_06_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_06_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_11_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_05_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_05_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_09_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_12_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_05_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_05_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_12_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_08_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_12_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_08_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2019_01_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_03_JizdenkoveAutomaty.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_10_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_03_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_04_JizdenkoveAutomatyKartaProvoz.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2017_02_JizdenkyPalubniJednotka.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2016_05_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/jizdenky/2018_06_JizdenkyDoplnkovyProdej.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2019_01_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_01_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_09_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_10_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_10_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_05_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_09_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_05_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_12_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_09_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_10_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_04_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_07_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_02_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_06_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_04_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_08_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_03_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_04_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_06_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_02_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_11_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2019_01_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_05_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_06_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_10_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_01_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_05_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_06_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_09_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_11_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_04_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_03_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_09_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_08_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_10_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_05_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_09_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2019_01_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_04_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_04_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_09_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_03_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_03_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_08_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_07_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_12_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_06_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_09_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_11_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_08_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_09_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_04_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_12_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_04_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_06_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_10_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_08_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_08_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_07_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_07_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_12_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_10_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_04_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_10_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_11_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_06_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_07_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_05_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_07_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_06_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_02_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_12_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_08_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_11_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_03_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_01_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_08_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_08_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_07_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_06_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_07_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_11_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_05_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_12_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_12_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_02_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_10_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_03_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_12_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_05_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_02_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_02_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_11_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_11_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_07_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_12_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2017_11_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_01_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2016_05_KuponyESHOP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_01_KuponyPapirove.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/DPP/kupony/2018_01_KuponyCipovaKarta.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/DOP/DOP_CykloZnacky_b/CIS_DRUH.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/DOP/DOP_Cyklotrasy_l/CIS_DOPR_STAV.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/IPR/supervizor/IPR_data_2016.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/IPR/supervizor/IPR_data_2017.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/DOP/DOP_Cyklogenerel_l/CIS_kategorie.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/GEO/GEO_RN_IndexPlochy_p/CIS_RN_INDEX.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/PUP/PVP_pp_pl_b/CIS_TEXT.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/PUP/PVP_pp_s_b/CIS_TEXT.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/URK/URK_SS_Podlaznost_p/CIS_TYP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/URK/URK_SS_Podlaznost_p/CIS_STRECHA.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/PUP/PUP_PODNETY_VSE_P/PODNETY.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/TIV/TIV_VDS_PPO_l/CIS_TYP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/URK/URK_SS_VyuzitiZakl_p/CIS_KOD.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/URK/URK_SS_VyuzitiZakl_p/CIS_VEREJ_PRISTUP.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/URK/URK_StavebniUzavery_p/CIS_KodTyp.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/URK/URK_StavebniUzavery_l/CIS_KodTyp.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/DOP/DOP_TSK_UlicUsPAKOM_l/CIS_SMEROVOST.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/DOP/DOP_TSK_UlicUsPAKOM_l/CIS_TYPKOMUNIK.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/PUP/PUP_UPRAVY_VSE_P/UPRAVY.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/DOP/DOP_ZPS_USEKY_p/CIS_TYPZONY.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/PUP/VPS_b/CIS_STAV.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/PUP/VPS_l/CIS_STAV.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/PUP/VPS_P/CIS_STAV.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/TIV/TIV_VDS_zaplava_DVT_p/CIS_KATEG.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/TIV/TIV_VDS_zaplava_VLT_p/CIS_KATEG.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/PUP/PUP_ZM_VSE_P/ZMENY.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/DOP/DOP_ZPS_ZonyStani_p/TARIF.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/DOP/DOP_ZPS_ZonyStani_p/TARIF.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/370ca929-5352-4f5d-aedc-be7bebfecbd9/resource/e99f2bfe-3951-4332-a8de-e76169ebf07e/download/7651a0d7-5950-4544-917f-4dcafed14008-kamery.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/9c4af39f-a430-42b8-b082-aa6f916d281c/resource/23da29c9-30db-47fe-816e-8e81af838db9/download/297271ea-c6e9-4786-bf1c-b7df1ef2ed45-kniha-doruenych-faktur-p-5-za-rok-2016.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/3f0c4726-4d1b-41f8-9a1c-aa05259a5e74/resource/75a467f8-4638-48ed-8ab9-9b0021b53104/download/381c40ba-fe8c-479b-8b70-a8110ea221b3-kniha-dorucenych-faktur-mc-praha-5-za-rok-2017.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/9ab7c9ff-129d-4b29-a301-702724b556e8/resource/defcac0f-b337-44d8-b974-10ac27f73541/download/b1b716c8-5e45-44cc-8b44-30a842212f2d-bigbelly_opendata.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/c9d0cc02-45b3-499f-865f-74b07ae77393/resource/68a06cb6-1801-4ccb-ac1a-a7ced20b273b/download/6d85ce28-614f-47c9-aac4-5bc3717081ba-prmrny-poet-nabijeni-za-den.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/c9d0cc02-45b3-499f-865f-74b07ae77393/resource/e4ac649b-8296-40cc-b2e4-699b6fc5467b/download/379fa46c-da54-49f7-8660-da94f5e1b5b9-alovo-nab.-celkovy-poet-minut-nabijni-na-obou-portech.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/c9d0cc02-45b3-499f-865f-74b07ae77393/resource/f0b794e2-fdc9-44db-ad6e-ba585e78c991/download/76028482-a91b-4258-a586-5035596a5ed9-pukinovo-nam.-minut-nabijeni.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/517f520a-439b-492a-afab-1e779c4254d5/resource/017a26cc-18ca-4548-8bce-4526a7f97d00/download/8759d2dd-296c-4977-ab41-3ed9c439867a-statistiky-adosti-1061999-sb.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/2d2df56d-4545-4370-b3af-3f7f8d8b8578/resource/5abedf3f-2a2b-4174-9711-0e970a9d9de1/download/aa0c1ffd-db58-4cbb-8047-a63cebbe4c8d-granty-2006-castky.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/2d2df56d-4545-4370-b3af-3f7f8d8b8578/resource/b0b63d16-60f5-4e26-834a-4e371d425177/download/f6ecd60a-fa86-4bf5-9ed8-ba0bc2efabb7-granty-2006-zadatele.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/2d2df56d-4545-4370-b3af-3f7f8d8b8578/resource/3aee029f-d612-4b5c-b161-e169482f6f6b/download/946f0cef-56ca-41b6-b8a5-2b41f480fbc5-granty-2006-usneseni.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/2d2df56d-4545-4370-b3af-3f7f8d8b8578/resource/bd70131a-d594-4fb1-9ddf-b9058c702882/download/3bde621d-4624-4806-bda2-207c5a31736f-granty-2006-projekty.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/2b5c6736-d78e-4215-9b2a-7f73afb10b0b/resource/f2f3bbaa-044d-4149-9434-1085436c90db/download/903a87a5-0f69-4fe8-82fd-e9ddb3c8725a-granty-2007-zadatele.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/2b5c6736-d78e-4215-9b2a-7f73afb10b0b/resource/ad19d2f6-6bc5-4feb-8a73-31d3119418d8/download/d7b5db24-c207-4893-9bd3-05af8171548b-granty-2007-usneseni.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/2b5c6736-d78e-4215-9b2a-7f73afb10b0b/resource/35ea2f89-702b-4ad7-9e48-0fc5ede5bebf/download/a195ab9e-1322-4c71-8b76-a3510c0f8f30-granty-2007-projekty.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/2b5c6736-d78e-4215-9b2a-7f73afb10b0b/resource/1d072913-16d6-48b2-bd14-9b9b9f5e5700/download/fc845520-c0ca-4663-a63e-2bb032b71cd5-granty-2007-castky.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/a4658c92-cbc7-42a9-b030-30b102799cfa/resource/c39fa87c-e78e-45e2-a89d-b1e7787cc890/download/edb12761-8645-4647-b96b-a5c3678d662f-granty-2008-zadatele.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/a4658c92-cbc7-42a9-b030-30b102799cfa/resource/4e929702-6032-485a-a975-e5632b040e3e/download/22b93307-1170-4294-b06b-0930e642d2bd-granty-2008-projekty.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/a4658c92-cbc7-42a9-b030-30b102799cfa/resource/e2939461-da4a-4490-b4dc-3e124cb2282c/download/fafdf043-4756-401e-a01e-e35300799687-granty-2008-usneseni.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/a4658c92-cbc7-42a9-b030-30b102799cfa/resource/97779883-6ba6-4dba-bccc-f2e170dab9c7/download/e0c7e064-ef62-4d1a-93f1-8a2faacdd606-granty-2008-castky.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/5e6fdeb6-7111-4b26-8fef-64d036de0a25/resource/34f79764-e828-49ed-925f-c186d2907365/download/54038e53-2ec4-4a40-b7d6-a5d060be9dd2-granty-2009-castky.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/5e6fdeb6-7111-4b26-8fef-64d036de0a25/resource/3753e0af-6d21-43ed-b0ad-0ce7f6edd2a1/download/1e593719-d773-4567-a705-2c48a8ea7c79-granty-2009-usneseni.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/5e6fdeb6-7111-4b26-8fef-64d036de0a25/resource/fd361f15-6105-4066-83ef-e9cc2b574ed9/download/b7de7a7d-8f85-4c2b-a8ee-ca61ff28ec16-granty-2009-projekty.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/5e6fdeb6-7111-4b26-8fef-64d036de0a25/resource/548f10be-cefb-45bc-b7d6-0512f7fb4070/download/66d86be5-4ec9-4687-95ce-ef2beafa57a3-granty-2009-zadatele.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/1a6c8a16-44f7-4c1d-b682-002afd826eeb/resource/f6a53ec3-a15d-4602-b3d7-0fb82f88b9bd/download/756a78c2-1ff3-442c-b74f-260e1f2144d3-granty-2010-zadatele.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/1a6c8a16-44f7-4c1d-b682-002afd826eeb/resource/7f46fc3a-93b4-4f91-8681-ab1031062f05/download/cbf0a31d-443d-499d-b3fa-73688beae17e-granty-2010-projekty.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/1a6c8a16-44f7-4c1d-b682-002afd826eeb/resource/f6ba1138-5b47-40a4-a225-7dd206bf3a86/download/9140ad7b-678a-482b-9a2b-1649dc1f6247-granty-2010-usneseni.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/1a6c8a16-44f7-4c1d-b682-002afd826eeb/resource/6ba92e48-4ff9-4419-85ab-9e1bc30357b8/download/1b33728e-79a7-4e7f-89ad-3502ddcbdd09-granty-2010-castky.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/cd618c63-fe47-4fc7-a158-34c3eb705298/resource/6ba6b490-dda2-475a-b999-931e3755d919/download/80b589c5-fae9-459f-a475-501cafc383e7-granty-2011-castky.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/cd618c63-fe47-4fc7-a158-34c3eb705298/resource/232c6adb-4808-4ca8-895f-221f06a07559/download/c6454949-42cd-4b25-8bd6-262d132a8d82-granty-2011-usneseni.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/cd618c63-fe47-4fc7-a158-34c3eb705298/resource/02702ae1-f9ab-46b3-9540-5a37cf6441fd/download/79f16930-6d2c-4eba-bb30-5521f3ebbed6-granty-2011-zadatele.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/cd618c63-fe47-4fc7-a158-34c3eb705298/resource/0993c9bf-3028-4381-ab25-1f42b3f5b552/download/6165e748-1f29-4d9a-94f3-c2f86bc385b7-granty-2011-projekty.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/781bf511-0956-47bd-b3ae-6d7c2b8cd27e/resource/1b8a39a3-0206-4761-a26c-77108792dff8/download/7ec55a5a-6c2d-4670-8360-8809098f326a-granty-2012-projekty.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/781bf511-0956-47bd-b3ae-6d7c2b8cd27e/resource/acb9dfe5-a25d-474f-9c06-cc499ac861ef/download/964dfe26-3ea5-499d-b3e5-6f92576f82a7-granty-2012-zadatele.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/781bf511-0956-47bd-b3ae-6d7c2b8cd27e/resource/19c2fc0a-21cd-4f24-aae9-c945962414e7/download/8fd53213-73d8-40c8-8bc8-2e6dfbcb5ba8-granty-2012-castky.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/781bf511-0956-47bd-b3ae-6d7c2b8cd27e/resource/cc23bd2e-2556-46fc-9888-7c3c8f09a66d/download/263ea289-7c11-4144-9235-1fbd32020c01-granty-2012-usneseni.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/1b44cd66-d6e7-40d7-8ae2-820a08b1e1d3/resource/368f0b8b-4f90-41bd-834f-69f83038a9c5/download/62921a14-06be-4cba-9a38-b711a935ef27-granty-2013-zadatele.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/1b44cd66-d6e7-40d7-8ae2-820a08b1e1d3/resource/23e22b6b-0b67-4950-88b1-874fb78a21bb/download/db3dd2a7-9f73-4144-b12d-b8c6b47e618c-granty-2013-projekty.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/1b44cd66-d6e7-40d7-8ae2-820a08b1e1d3/resource/34f2d8f0-b30a-4936-ac76-b22c34fb2aa6/download/1f548624-dc43-45e1-ab8a-da0171b4cae5-granty-2013-castky.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/1b44cd66-d6e7-40d7-8ae2-820a08b1e1d3/resource/2ddeec71-dc71-42ad-8489-0569bd798d62/download/a63944b3-f13b-44ef-9aa2-0b2e0926b5f6-granty-2013-usneseni.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/0c1224aa-710b-401a-88ca-7ac7197b4210/resource/2251632d-9c87-4461-88ed-0bdc47a9ff87/download/074aa1cb-9369-4879-af9f-c90800990e8d-granty-2014-usneseni.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/0c1224aa-710b-401a-88ca-7ac7197b4210/resource/33f809d8-66f4-4217-8a68-436a00976c2d/download/7ed8cbcd-06b6-4eb6-abb3-0e16ce937d15-granty-2014-castky.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/0c1224aa-710b-401a-88ca-7ac7197b4210/resource/6de16384-7940-489f-8ed3-165c4c4070bc/download/c07b1cd4-42c9-458d-92ed-92ba5a4b2cbe-granty-2014-projekty.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/0c1224aa-710b-401a-88ca-7ac7197b4210/resource/cf5fbb17-9bb0-46a8-99c6-a4ddbc8e715c/download/142f67d1-0e01-4788-a75b-fa995de029ec-granty-2014-zadatele.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/920ce749-a5fe-4a19-a61f-4f3e98c1daf6/resource/d544288d-67c7-41f8-a928-1ed9f9590a59/download/15e920f8-20b6-4ee3-93df-d4f4214deeba-granty-2015-usneseni.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/920ce749-a5fe-4a19-a61f-4f3e98c1daf6/resource/539ab310-1a5b-4ce2-9f9a-c3e545b4f0ed/download/6385f0ea-41d9-41c1-9895-40dcb654406f-granty-2015-projekty.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/920ce749-a5fe-4a19-a61f-4f3e98c1daf6/resource/e09d8064-1a5d-4db6-9a1c-945d845c5847/download/35a97770-eb0b-4f23-b514-e061cd5068fa-granty-2015-zadatele.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/920ce749-a5fe-4a19-a61f-4f3e98c1daf6/resource/f1ff101f-4143-4c5c-8d24-59d78bc33dd8/download/f8f58dae-e174-412a-98a9-2f9244ba78b1-granty-2015-castky.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/af5ac2b3-e477-4609-b371-5ec04c1e92b5/resource/e61b0e59-eee8-4b57-a469-bad5a79af33e/download/af318ddc-9d5c-4fa8-aee1-26cd6261ba0c-granty-2016-zadatele.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/af5ac2b3-e477-4609-b371-5ec04c1e92b5/resource/8ba920c0-966d-4f23-8a43-3825a394ebcc/download/ce34d329-5624-43c5-b261-c2fc0b4059b2-granty-2016-usneseni.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/af5ac2b3-e477-4609-b371-5ec04c1e92b5/resource/6b91c372-db94-4d89-a598-7ecfd7a42f53/download/1664ba6b-2635-42f8-b5ba-fe45edfd6bdd-granty-2016-castky.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/af5ac2b3-e477-4609-b371-5ec04c1e92b5/resource/b9e6c8fb-ae9e-4fa7-9e0f-875bb69f6fa2/download/67b6fe5c-895f-4821-b069-36e6ed7c3bda-granty-2016-projekty.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/0da08127-b1e6-48bf-8372-886b2cddcff1/resource/8d90741b-7da9-4eae-b36d-d4f96dea2a47/download/cbc44834-75ca-4bb8-bf16-e88d14160ad6-granty-2017-projekty.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/0da08127-b1e6-48bf-8372-886b2cddcff1/resource/f93ea07b-121f-454f-8cc1-69af51912a78/download/1ab1563c-f194-48ef-905b-16a74b3ed358-granty-2017-castky.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/0da08127-b1e6-48bf-8372-886b2cddcff1/resource/2d7d2d72-145d-453c-9993-3427496545aa/download/d9d5e243-cb75-474a-97b2-b63874f9782a-granty-2017-zadatele.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/0da08127-b1e6-48bf-8372-886b2cddcff1/resource/fe144e06-dd10-409e-8767-8ed2d5e57d2e/download/97554f62-845f-4c12-a52d-00ea2a363401-granty-2017-usneseni.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/5cdac5fd-ee4c-4a52-9cbd-7974ca80bfe4/resource/ba4a9162-29d3-453a-b8e9-a1a3baa26b21/download/ad199d62-495d-4028-867e-9199fe735d00-granty-2018-usneseni.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/5cdac5fd-ee4c-4a52-9cbd-7974ca80bfe4/resource/ef13a4ad-9e63-4ecd-af75-d4760c058543/download/a908f06c-91ee-4e98-960e-6409694a8b43-granty-2018-castky.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/5cdac5fd-ee4c-4a52-9cbd-7974ca80bfe4/resource/f4c50e01-cb48-4161-a39a-47caed4dc783/download/25c1cdc3-c54e-46d7-a163-06b2bb1bfc72-granty-2018-projekty.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/5cdac5fd-ee4c-4a52-9cbd-7974ca80bfe4/resource/62da421d-ccca-41cf-a40c-6fdadcc96e57/download/40212ee6-dcab-4c0e-990e-32f035cec0a7-granty-2018-zadatele.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/b281c3e8-b5a6-4efa-8a4c-a6e815d7784e/resource/c6eada50-3d69-48f8-a93e-16e7354a3557/download/de5a68e7-e357-4d1d-81f8-cdbe186069b2-granty-2019-projekty.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/b281c3e8-b5a6-4efa-8a4c-a6e815d7784e/resource/ff363119-d89a-4adc-b346-494089564f70/download/2365ab32-f0f5-4033-aaa2-4ce4f6dcc38f-granty-2019-zadatele.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/b281c3e8-b5a6-4efa-8a4c-a6e815d7784e/resource/c7f8dc69-4514-4886-901d-91330deab5db/download/cec3693c-8a09-4b97-be85-cc7ad8dc4202-granty-2019-usneseni.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/b281c3e8-b5a6-4efa-8a4c-a6e815d7784e/resource/7556a7c9-2852-407e-acdd-185f4161c059/download/e1981916-a1f7-4337-b3a5-9d884ffa6817-granty-2019-castky.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/1cd09457-161f-411c-a65a-85ead66ac33c/resource/9362ff19-2647-459c-9008-3b0b57a665c2/download/af0b3e93-7e03-48fb-847c-660982838586-signaturove-skupiny.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/1cd09457-161f-411c-a65a-85ead66ac33c/resource/444214d1-8e6d-4c22-b73a-3c9b79f9f80e/download/6234e0ca-71b4-4e03-ac86-4450a73eb8ae-oborove-skupiny.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/396e9ed8-fba8-4be5-af7d-93d2d34af07b/resource/bf7d5635-ac86-4ecf-b30b-a46523aa7ddd/download/eb88fca0-93e2-4945-87c4-f440afc726da-vypujcky-1999.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/396e9ed8-fba8-4be5-af7d-93d2d34af07b/resource/e2a1e4a0-1465-4603-8baf-e09cc7ce697d/download/037c9f5e-9d88-4604-8a02-732a54ea53c1-vypujcky-2004.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/396e9ed8-fba8-4be5-af7d-93d2d34af07b/resource/372af7bb-b5cc-46c6-9f6c-6a2e4da4429a/download/e5523017-5d0e-420b-b965-1ca40f505a7c-vypujcky-1998.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/396e9ed8-fba8-4be5-af7d-93d2d34af07b/resource/4410c397-d7c5-4ce1-85c2-b1b36a88dce2/download/d93f1cf6-8a85-41c1-8eb3-73bb0000eaff-vypujcky-2007.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/396e9ed8-fba8-4be5-af7d-93d2d34af07b/resource/dd565472-ce79-48c8-b540-0173ca579dba/download/37231671-87a6-4682-a3d7-eaebd1363bb6-vypujcky-2015.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/396e9ed8-fba8-4be5-af7d-93d2d34af07b/resource/d3d01387-d397-4b52-85c3-8201b87caf08/download/5927f8db-abd5-45c9-847d-800e1433e0fb-vypujcky-2000.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/396e9ed8-fba8-4be5-af7d-93d2d34af07b/resource/d3747a28-11c6-45ec-aaff-893dfe0f0a38/download/bf06c96e-9e61-4208-a668-bebda8c3c05b-vypujcky-2001.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/396e9ed8-fba8-4be5-af7d-93d2d34af07b/resource/375d0cc8-fa06-457c-9b2c-11d4adcd7a7a/download/15802926-37e9-4937-85d1-115a02cbb83d-vypujcky-2011.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/396e9ed8-fba8-4be5-af7d-93d2d34af07b/resource/9d05d895-68b3-4d61-b27b-5c0cd49f1055/download/120bbeb6-7d96-4d5b-ad6e-91bbe03b2494-vypujcky-2005.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/396e9ed8-fba8-4be5-af7d-93d2d34af07b/resource/6d81b67e-173b-474b-838b-9b19f62eada2/download/d6439b8f-72f0-41c6-8c93-7087ef567524-vypujcky-souhrn.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/396e9ed8-fba8-4be5-af7d-93d2d34af07b/resource/6f5b4d26-d8aa-43de-a940-d503522c2a4e/download/8649915c-78a8-493f-b211-cd77388c62cd-vypujcky-2013.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/396e9ed8-fba8-4be5-af7d-93d2d34af07b/resource/d03c2cba-6326-46da-b8a1-97805b84fe35/download/a84f4402-690e-489b-8108-752de39c5ce5-vypujcky-2008.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/396e9ed8-fba8-4be5-af7d-93d2d34af07b/resource/55795bdf-68b1-4ff8-8103-ed46b8c81b3d/download/e57590bf-c05f-4a83-ba2e-4b75eb56b02f-vypujcky-2006.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/396e9ed8-fba8-4be5-af7d-93d2d34af07b/resource/bd928088-442b-460f-a11d-aa33fb6e2bc9/download/8f20b894-a001-490f-bb0c-170859abcfe7-vypujcky-2009.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/396e9ed8-fba8-4be5-af7d-93d2d34af07b/resource/6798181a-37ea-4a09-a2b0-6bfa84f8f436/download/646f13e8-8eeb-404b-8a76-60f45d48c072-vypujcky-2012.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/396e9ed8-fba8-4be5-af7d-93d2d34af07b/resource/b2ca0b03-a5b9-42f9-9a4d-08cbf2236e5b/download/9ef25ffd-6f93-4d2a-98e5-2bc1a43bcc3a-vypujcky-2014.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/396e9ed8-fba8-4be5-af7d-93d2d34af07b/resource/286278da-aaae-41bf-9b46-136c52df5eac/download/95a87a86-8a7f-4660-8fbc-18abf3bb22cc-vypujcky-2002.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/396e9ed8-fba8-4be5-af7d-93d2d34af07b/resource/3ff71cdc-7738-4329-84f2-b5a68e55c95f/download/5eaed0f7-f4a5-473c-86ca-cc9a059e0969-vypujcky-2003.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/396e9ed8-fba8-4be5-af7d-93d2d34af07b/resource/569ff721-afa7-4d7b-9848-b23035f5e3c8/download/051357cd-87d8-4e5c-9b95-bdfd4bc6e1a9-vypujcky-2010.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/FSB/BEZ_OkrskyMPP_p/Okrskari.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/37e9c461-cecd-4a90-969b-7b7901d9ca08/resource/17e3ac91-9133-4fa9-a0e5-afc1500eac4a/download/7b751763-6691-4d66-b584-1692d6d11728-validcoupons.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/37e9c461-cecd-4a90-969b-7b7901d9ca08/resource/12fdba78-587b-4112-b317-a9f5f812ef8f/download/98516901-cc81-4b1c-8672-45184ccad487-expirations.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/37e9c461-cecd-4a90-969b-7b7901d9ca08/resource/c10520f0-39bf-4370-af9f-88597d979dd7/download/572b74bd-e89d-4eaa-81ec-25da2f9b2b3c-cardstates.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/37e9c461-cecd-4a90-969b-7b7901d9ca08/resource/876a4195-a67e-46b3-9c68-85eef2aeeb6d/download/0d4ebe8b-a39f-4cc4-ad45-9e809f1098fa-applications.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/e71aca6a-e7d8-43b5-b04d-a883fe789b95/resource/bed3ff5d-3d84-424c-91d1-73f7ad2b9409/download/d0eaca9a-33e3-445e-a552-9c2f879cddcf-kniha-doruenych-faktur-p-5-za-rok-2015.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/c5679ceb-95fa-41e6-ac20-a1768222009a/resource/31ab7adb-272a-4286-a072-2ec673a597b4/download/89fcd8fe-77c6-4412-ba37-39dc8a136279-kdf2011a2015.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/0cce4908-b03b-4ebd-9ea8-236b605f8a27/resource/8c7020cd-0d5c-46df-911c-7fa85c24e481/download/361b6458-cd59-4ebc-adf3-c5b3613f8731-p6kdf140915-31122015.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/0cce4908-b03b-4ebd-9ea8-236b605f8a27/resource/77fcb341-7a4f-47e9-b270-ab3f55eaa49c/download/a62d01dd-0307-476a-bda9-18be2a9505ea-kopie-p6-kdffaktury2015.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/472c20ce-62cb-47ac-9504-0365dcbc54bc/resource/1f8718f6-968f-4df9-af3d-40c02e3d8947/download/7e2fcb42-7ee0-41bf-bceb-a14e2b9b639c-kdf2016.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/dd21785d-9ac0-4680-8378-64deab538015/resource/60fa38f3-ddab-46a9-80c9-9acd95e8d364/download/95239d07-7536-43df-bf9d-80de654c399b-fakturyrok2017.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/85419e79-4868-41b3-bfbd-58421a8328a5/resource/8e42fae8-8065-47b3-9b92-6635fef63dc2/download/200c155a-f0cf-4cc2-8130-445a1433635e-p6smlouvy2015.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/8dbb0d35-692c-4668-b225-c0702853c28e/resource/c4f3157a-ef07-423a-9051-7c748484d6df/download/5d88f52c-78ee-4de3-ad18-9b844737cd63-parking.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/8d97e4cc-9e3d-4e84-bb9c-6db5656cfbd3/resource/e71c2c7f-372e-4e1b-b324-9bfbefad8028/download/73ae3cad-73a6-4f84-a062-b27d310c909c-poty-dti-m-2015-2016.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/8d97e4cc-9e3d-4e84-bb9c-6db5656cfbd3/resource/ccca7eb3-25d5-4940-80a0-f5923d8fc75b/download/4cef2a18-fd66-4d22-bedf-bd244138acec-poty-dti-m-2016-2017.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/8d97e4cc-9e3d-4e84-bb9c-6db5656cfbd3/resource/d1eef72b-fea3-4713-8ef7-87473155eb89/download/54971ab6-d03c-47c2-a7dc-6bdf5bebba71-poty-dti-m-2017-2018.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/4ac8aa4b-bbe6-49d8-9ba7-86306e4b96c9/resource/088cf6d2-2ad1-43c9-ae38-d2f5b84fbdbf/download/680e3232-b418-46c6-a8b2-7dcb49781da5-mp21-kdf-2011.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/a4f4edaa-aa3d-46e8-ae77-4215438ea0b4/resource/67d18fbf-2a91-47ba-848c-a53bf683130a/download/e1c714b1-3d2a-464e-a1db-fe77b2949f4a-13-19-list1.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/a4f4edaa-aa3d-46e8-ae77-4215438ea0b4/resource/5b50ef8e-7644-4e22-a8e8-3bcc8d0df5ff/download/286fc518-a763-46e5-81e2-3958bbdedbf2-13.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/2f421880-260d-4ab5-bf02-d4dc2f0e6f2e/resource/333302e5-a97a-4e7f-bc49-8e7dd717cc0d/download/5390d539-0a19-40d7-960d-19b812eaacb0-1cp8-vizitkyvse.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/2f421880-260d-4ab5-bf02-d4dc2f0e6f2e/resource/b4cc3317-667c-4702-bf0c-e54da1450177/download/4573d8ad-d09b-4f59-84c7-e637dad2a289-1ainformacestruktura.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/2f421880-260d-4ab5-bf02-d4dc2f0e6f2e/resource/d39b9da2-7412-4444-99dc-f6123dfad53c/download/dfadac1f-3b30-459d-bb07-3209dbda84e8-pracovnici.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/d3ee8400-a09f-4dcf-bafe-6f17e58f33cd/resource/17dfb202-7166-42e9-9802-522e0a11e001/download/056a4b8b-f38c-4ce2-92db-e537e1879f93-poty-ak-z-2017-2018.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/d3ee8400-a09f-4dcf-bafe-6f17e58f33cd/resource/c6df9497-8ad7-45eb-9e27-8bd757f312e2/download/1b2c6ba9-9b05-4060-b014-0b20d047a56e-7a.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/d3ee8400-a09f-4dcf-bafe-6f17e58f33cd/resource/3c852e2b-da1b-43ee-bce9-ae10d5269a3c/download/508c2ee4-f796-44f7-a274-5f56b548b0a3-poty-ak-z-2016-2017.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/52d80637-9b43-4181-9c9c-fce73bbd443a/resource/0dc05aab-d4af-423d-ac1e-fa3f7c168472/download/43cddad0-314c-44c3-9df5-be661dd6724d-povoleni-k-lovu2017.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/52d80637-9b43-4181-9c9c-fce73bbd443a/resource/f92f94ef-8989-451b-bd1c-2abd71e521ea/download/e80bca64-64b3-48cd-b831-e35fd528a8dd-2.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/048f4737-0348-41e0-9f1f-7ca1adfa5e9b/resource/be9bc291-645e-4c8d-a0d1-ba206a05f033/download/77092d58-b39b-4159-a315-ddd4219cf300-4.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/048f4737-0348-41e0-9f1f-7ca1adfa5e9b/resource/12ab7032-c897-4091-9a15-c17e5a52db2f/download/53eca567-ea55-4ef4-a69b-1d1701277410-sbrna-mista-pro-odpad-a-umistni-kontejner2017.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/28e02a6b-9f40-410c-a898-51289607dafb/resource/bfe2e59d-b592-47c3-8df6-978c40f050c8/download/45d24d78-3e5f-4c49-a50b-10b224d028cc-m-2017-2018-kontakty.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/28e02a6b-9f40-410c-a898-51289607dafb/resource/b11398e2-8d4f-4d0a-ab12-43c2a4d6c680/download/86c0c3cb-1fd9-4de9-8f2f-ed5ce961059e-m-2016-2017.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/5207d9bb-41f0-48a7-920b-174931d365e9/resource/b19fd0bc-36cc-4a19-9432-206193183dc1/download/170f5039-f872-4ce1-b1c8-a304eb53a254-socialka.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/5207d9bb-41f0-48a7-920b-174931d365e9/resource/f9bd7e5a-119f-4810-a9d3-575f2db94d49/download/380e711c-a479-4fcf-98ee-abbd25845247-open-data-osvp8.csv-open-data-osvp8.csv.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/846dd85c-6657-4c7e-9942-c1682d199edb/resource/bfec2a39-98b0-4188-9f7a-8ab76997267d/download/9c16fc84-13c3-478c-be35-b9ea455f2d77-z-2016-2017-kontakty.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/846dd85c-6657-4c7e-9942-c1682d199edb/resource/1b47357d-6ee2-415c-bc2e-bef1a49cb515/download/690f3d9c-1bfb-4473-8bf6-ceef736a3d7b-z-2017-2018-kontakty.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/846dd85c-6657-4c7e-9942-c1682d199edb/resource/21a031d2-7993-46a8-b0b3-5005504e1b8c/download/f922064b-d4bc-4a59-88b6-822df305d4de-71.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/47a92b6f-f55d-4351-9f05-99f98c5819e2/resource/5116da8e-be76-4ba4-b2ff-5f6591fd7363/download/5e8b78d4-2ead-4fb3-9023-7511ecb982da-sportovit-2017.xlsx-list1.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/47a92b6f-f55d-4351-9f05-99f98c5819e2/resource/e3815cb7-3730-42c3-9700-316e1602b907/download/30ab23cc-c036-43a9-bbb5-38f8586f74ab-8.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/a9a0ece0-0e3d-46f8-83b5-8edd2371321a/resource/4afdc983-1762-473e-a353-d9c70392171f/download/8065266a-2b17-4741-93a9-08e7e1fe31f1-10zachodky-list1.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/a9a0ece0-0e3d-46f8-83b5-8edd2371321a/resource/ee0c5c9d-a57c-4194-afc3-589dde48fb0c/download/f0888804-41d9-471b-85ef-da6cd17f19ad-zachodky-2017.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/ecbe88f9-e691-4cc3-8274-80307d783fc0/resource/0610cbee-6a75-4a18-b7c2-138da27c4ea1/download/9c69ad59-163c-4735-a4de-61805876a790-v-personalistika-praha8-2017-list1.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/9a7f5eee-7769-4ae0-b703-03760d8d92ce/resource/4a83fa95-d37e-40b5-9c89-ad61eee91638/download/344c930c-7b21-4fd9-a7a8-614364909bff-farmarske_trhy.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/9a7f5eee-7769-4ae0-b703-03760d8d92ce/resource/502d6a40-cc41-4ef2-bb75-b1ce881cd198/download/7d16e773-567c-47bb-a24d-59f916f1054b-komunitni_zahrady.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/9a7f5eee-7769-4ae0-b703-03760d8d92ce/resource/cc14a44f-02e2-4345-a1b0-eb595e5a5a42/download/94475154-8235-47b4-8f85-2e16a43f16ae-bio_obchody.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/9a7f5eee-7769-4ae0-b703-03760d8d92ce/resource/82849c14-3bc0-43de-bfcb-341b10954d49/download/9af25799-1261-40b7-a62f-65963503dc86-vcelnice.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/9a7f5eee-7769-4ae0-b703-03760d8d92ce/resource/a1f68964-8148-4a94-b71b-2a61bfa969ef/download/d26fd5ff-41e9-4cd4-87bd-d70eabf60b9e-komunitni_lednice.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/7ae3465c-a7be-4f74-82e6-1ddb0d763cf1/resource/48659166-2112-458d-a715-a4701414a271/download/02265fb1-99e5-493c-9a1d-4bca67ee018e-smluvy2016.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/f8241e74-3a02-4e88-b4ed-cfc628a4128c/resource/82e46afc-3aed-43fe-8d93-9ea83f767bcd/download/b0beaade-cea4-4bc5-8927-94eef96ef5d5-rocnistatistikapidopendata.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/7b252b1b-4551-43fb-b738-5480c48ed3b7/resource/5d1ee13f-f6e9-4ee9-a1bd-48d5ca2bb867/download/ed96f581-a668-4fd5-94cc-c3682285da0b-databaze-sportovi-a-klub.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/96dd2d94-20f6-47cd-9747-d41acac52525/resource/78dbd3a0-74e1-4ec1-8feb-799f8515fb1d/download/4fd31441-295d-445e-b763-ee71b6aee73b-odtahy-2010-2014.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/96dd2d94-20f6-47cd-9747-d41acac52525/resource/52f92ddc-525a-44d4-877b-b33b1dca2ce9/download/29546e0b-6c33-4b01-8cd6-207a4e7738d7-odtahy-2015.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/00df7353-f41b-492e-b916-1c5016b50dd2/resource/568ec129-62ef-4f2d-84e7-9d067524aff8/download/df1fac74-2dd6-4702-b78a-9de2d60c04e0-opendata-expirace.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/00df7353-f41b-492e-b916-1c5016b50dd2/resource/2b7b64bb-f15e-4bc3-b97f-d43a7338dc5d/download/3df725c6-e8be-4981-ab1f-4a17cee7217d-opendata-vymeny.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/00df7353-f41b-492e-b916-1c5016b50dd2/resource/121098ef-02f6-4241-8ee0-40271aeb8529/download/e85783c1-5478-4175-8574-065781290f80-opendata-aktivni.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/40bbb955-2b3c-4eef-b655-ce67411d20eb/resource/c97156ee-afa0-45de-b0b0-0ac9421c2c84/download/1b2231e8-e0ef-4062-871d-8e958fd28faa-komunalni-odpad-vyuziti-isoh-mzp.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/40bbb955-2b3c-4eef-b655-ce67411d20eb/resource/3464c484-faba-48ca-b1f9-c653d76258db/download/59536015-7d45-465e-83b2-303fe3ff6590-statistiky-trideneho-odpadu-portal-zp-hmp.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/40bbb955-2b3c-4eef-b655-ce67411d20eb/resource/54372b1b-c027-4dc1-9618-09e1e6b610ce/download/1419f3b3-2e83-40fe-b3de-7294975efc42-katalog-odpadu.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/fcbc860a-b111-43d3-9979-4a419bb281fb/resource/f5b30f2c-8a6c-4597-87e7-76e801cce4de/download/8d04cae5-3968-4fdd-9574-4cb0c3b73ed2-meteocidla.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/3ab02b45-2897-4d05-a84c-6b0b798c8090/resource/39718521-ceee-4499-82ad-03c768e43d56/download/2c2cf33e-a8ae-4f19-95c7-786974a35a19-fve.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/3ab02b45-2897-4d05-a84c-6b0b798c8090/resource/6b3d1bf0-7a71-456c-9037-775e9144e793/download/8f9c0e41-8274-4c6f-b066-694f09d2e3d8-ekobudovvy.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/69a7fbbb-036b-443e-8b8f-b1cb40cbdeb0/resource/88453b99-d385-461d-aa64-8dff342208fa/download/648c9d36-317a-4fc9-bf66-f34168a9bb01-uredni_deska.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/69a7fbbb-036b-443e-8b8f-b1cb40cbdeb0/resource/1bb888ee-90d0-46d0-85ef-65d8347845e4/download/bcaadfd0-4879-4033-95dc-9aca0f6205c8-urednideska.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/69a7fbbb-036b-443e-8b8f-b1cb40cbdeb0/resource/387b4f3d-2577-4552-9821-7ac88931d851/download/4e23390c-044e-4558-aa0a-1043fb8afcc8-odbory.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/69a7fbbb-036b-443e-8b8f-b1cb40cbdeb0/resource/cd2c958e-9e95-4a99-b22a-212b7811d71f/download/2d41ef89-ce33-4fb9-8b5d-770c4ebfeb48-deska2012-2016.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/DOP/DOP_PID_ZAST_POPIS_TS_B/CIS_ZAST_DD.csv"
    },
    {
      "tabularUrl": "http://opendata.iprpraha.cz/CUR/DOP/DOP_PID_ZASTAVKY_TS_B/CIS_ZAST_DD.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/cddf7030-c439-4251-a47c-43cb5372b675/resource/dec3e4fb-b624-4f55-80a0-4713d5800481/download/924a36cc-6286-49f8-b8c3-12782823797e-akcezoo.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/8a92abe2-8466-43f1-aedb-1eb953b720df/resource/4fc2aaff-3e7b-4d24-94d7-713a1f45074c/download/b91ccc1c-528c-4269-90d1-daccd2843d21-lexiconcomplete.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/8a92abe2-8466-43f1-aedb-1eb953b720df/resource/e3cd5857-d62e-44f0-8414-17721808c62e/download/dcd452c1-24b9-43bc-b0ef-3a64e6976428-lexiconfoodb.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/8a92abe2-8466-43f1-aedb-1eb953b720df/resource/05702365-3670-4e52-9b57-fae2ef3f6275/download/4711a739-bd40-40fe-9be0-bc1c9681fcb4-lexiconlocalities.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/8a92abe2-8466-43f1-aedb-1eb953b720df/resource/6316e78d-d8d2-404a-8741-91cc1395c6fd/download/7d168acc-4db4-4830-a59a-a57415303bc6-lexikoncontinentsvazba.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/8a92abe2-8466-43f1-aedb-1eb953b720df/resource/c650683e-a529-4ef2-9284-110521aa4bf9/download/27118703-392e-4267-bc94-765dbeb1f8d5-lexiconfood.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/8a92abe2-8466-43f1-aedb-1eb953b720df/resource/90e66377-9d31-4852-8cfb-1981319ccb20/download/d798a1a1-3173-4cb5-9691-af1cf44c070f-lexiconclass2.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/8a92abe2-8466-43f1-aedb-1eb953b720df/resource/898c46ec-952b-4584-aafa-cd6276274085/download/d7c63960-b578-49fe-b3b1-54c177a780f8-lexiconlocalitiestypes.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/8a92abe2-8466-43f1-aedb-1eb953b720df/resource/fead1025-57df-44e9-a9f7-e2d856cdabb1/download/3b4d38c0-da26-4d08-a8b8-bbce949712e1-lexikoncontinents.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/8a92abe2-8466-43f1-aedb-1eb953b720df/resource/0101948d-bacc-42ac-97d2-803b08252057/download/d0792cb4-6227-4f5d-b09e-693394cfcbbd-lexiconbiotopesvazba.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/8a92abe2-8466-43f1-aedb-1eb953b720df/resource/fdeb7466-3c2b-4cd7-8e7a-02bb4314b481/download/f2dd58ab-ef76-4c13-8ca2-06d969b33531-lexiconbiotopes.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/542a1536-2b55-41db-b4ed-70c4b0dbaaee/resource/7d36444a-a3f2-40fc-85f0-1c82f33d9602/download/c8948c01-974c-4f84-83db-1ce3274a0f7c-zoo-navstevnost.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/62353418/104940036/010022-19data031919.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/62353418/73590208/012052-18data121418.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/62353418/74230342/020050-18data053118.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/62353418/74123179/050101-18data011419.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/62353418/92011112/070013-19data032519.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/10180/61566288/130140-17data2015.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/10180/61566288/130140-17data2016.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/10180/92151633/130140-18data2017.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/10180/62582000/130141-17data2014.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/10180/62582000/130141-17data2015.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/10180/62582000/130141-17data2016.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/10180/83871777/130141-18data2017.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/62353418/83879838/130142-18data051818.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/62353418/104938592/140133-19data013119.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/62353418/108720480/150196-19data031519.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/10180/62327313/170242-17data.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/62353418/108720476/200075-19data031519.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/62353418/108643849/200076-19data031519.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/62353418/104940715/241048-19data031119.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/62353418/73345465/250169-17data011518.csv"
    },
    {
      "tabularUrl": "https://vdb.czso.cz/pll/eweb/stapro.csv"
    },
    {
      "tabularUrl": "https://data.army.cz/sites/default/files/1.1.2015%20-%2031.12.2015%20sestava%20tendermarket_0.csv"
    },
    {
      "tabularUrl": "https://data.army.cz/sites/default/files/1.1.2016_-_31.12.2016_sestava_tendermarket.csv"
    },
    {
      "tabularUrl": "https://data.army.cz/sites/default/files/Uhrazen%C3%A9%20faktury%20k%2031.1.2018_0.csv"
    },
    {
      "tabularUrl": "https://data.army.cz/sites/default/files/Uhrazen%C3%A9%20faktury%20k%2028.2.2019.csv"
    },
    {
      "tabularUrl": "https://data.army.cz/sites/default/files/Uhrazen%C3%A9%20faktury%20k%2031.12.2018.csv"
    },
    {
      "tabularUrl": "https://data.army.cz/sites/default/files/Uhrazen%C3%A9%20faktury%20k%2028.2.2019.csv"
    },
    {
      "tabularUrl": "https://data.army.cz/sites/default/files/Uhrazen%C3%A9%20faktury%20k%2031.12.2016%20k%20nahr%C3%A1n%C3%AD.csv"
    },
    {
      "tabularUrl": "https://data.army.cz/sites/default/files/1_12_2015.csv"
    },
    {
      "tabularUrl": "https://data.army.cz/sites/default/files/Uhrazen%C3%A9%20faktury%20k%2031.12.2017%20k%20nahr%C3%A1n%C3%AD.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/ciselnik-datovych-typu.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/ciselnik-druhu-akci-v-tiskopise-onz.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/ciselnik-druhu-pracovnich-cinnosti.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/ciselnik-druhu-zamestnani-pro-e-neschopenku.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/ciselnik-duvodu-k-ukonceni-pracovni-neschopnosti.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/ciselnik-okresu.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/ciselnik-pohlavi.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/ciselnik-specifikace-cizozemskeho-nositele-pojisteni.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/ciselnik-zdravotnich-pojistoven.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/doba-rizeni-o-namitkach.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/duchodci-v-cr-krajich-okresech.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/duchody-dle-veku.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/invalidita.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/kontroly-plneni-povinnosti-zamestnavatelu-v-oblasti-socialniho-zabezpeceni.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/minimalni-vymerovaci-zaklad-osvc.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/nejcastejsi-priciny-vzniku-invalidity.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/nove-priznane-duchody-dle-osobniho-vymerovaciho-zakladu.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/nove-priznane-duchody-dle-veku.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/nove-priznane-duchody-dle-vyse-duchodu.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/pocet-duchodcu-s-exekucni-srazkou-podle-kraju.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/pocet-duchodcu-s-exekucni-srazkou-v-cr.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/pocet-duchodcu-s-vyplatou-do-ciziny-dle-statu.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/pocet-duchodu-s-vyplatou-do-ciziny-dle-statu.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/pocet-muzu-a-zen-v-cssz.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/pocet-namitek.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/pocet-podanych-eldp.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/pocet-predcasnych-starobnich-duchodu-a-vydaje-v-cr.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/pocet-spravnich-exekuci.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/pocet-sto-a-viceletych-duchodcu-podle-kraju.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/pocet-sto-a-viceletych-duchodcu-v-cr.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/pocet-vyplacenych-dnp-podle-kraju.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/pocet-vyplacenych-dnp-podle-okresu.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/pocet-vyplacenych-dnp-v-cr.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/pocet-vyplacenych-invalidnich-duchodu-v-ceske-republice.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/pocet-vyplat-duchodu-s-exekucni-srazkou.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/pocet-vyplat-priplatku-a-prispevku-k-duchodum-dle-odskodnovacich-zakonu.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/pocet-vyrizenych-ioldp.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/pocet-vystavenych-potvrzeni-o-prislusnosti-k-ceskym-pravnim-predpisum.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/pocty-prihlasenych-pohledavek-cssz-do-insolvencniho-rizeni.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/pomocne-ciselniky.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/posudky-provedene-lps.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/prehled-o-celkovem-poctu-osvc-podle-kraju.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/prehled-o-celkovem-poctu-osvc-podle-okresu.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/prehled-o-celkovem-poctu-osvc-v-cr.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/prehled-o-poctu-zamestnavatelu-pojistencu-a-pojistnych-vztahu-podle-kraju.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/prehled-o-poctu-zamestnavatelu-pojistencu-a-pojistnych-vztahu-podle-okresu.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/prehled-o-poctu-zamestnavatelu-pojistencu-a-pojistnych-vztahu-v-cr.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/prum-delka-pobirani-s-duchodu.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/prum-vek-u-nove-priznanych-duchodu-dle-druhu.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/prum-vyse-duchodu-u-nove-priznanych-duchodu-podle-druhu-duchodu.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/prum-vyse-osobniho-vymerovaciho-zakladu-u-nove-priznanych-duchodu-podle-druhu-duchodu.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/prumerny-plat-v-cssz.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/rozhodovani-o-promijeni-penale.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/rozlozeni-souboru-duchodcu-podle-vyse-duchodu-v-kvantilovem-vyjadreni.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/sazby-pojisteni-v-cr.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/statistika-zadosti-dle-zak-106-1999-sb.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/systemizovana-mista-a-fluktuace-zamestnancu-cssz.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/udaje-o-cinnosti-call-centra-pro-dp.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/udaje-o-cinnosti-call-centra-pro-np.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/udaje-o-plneni-podilu-osob-se-zdravotnim-postizenim.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/ukazatel-pracovni-neschopnosti-podle-delky-trvani-dpn-a-kraju.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/ukazatel-pracovni-neschopnosti-podle-delky-trvani-dpn-v-cr.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/ukazatele-pracovni-neschopnosti-podle-kraju.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/ukazatele-pracovni-neschopnosti-podle-pohlavi-a-diagnozy.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/ukazatele-pracovni-neschopnosti-podle-pohlavi-a-vekove-skupiny.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/ukazatele-pracovni-neschopnosti.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/vydaje-na-duchody-v-cr.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/vyplacene-duchody-dle-vyse.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/zamestnanci-cssz-dle-veku.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/zamestnanci-cssz-dle-vzdelani.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/zanikle-duchody.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/zmeny-mezi-stupni-invalidniho-duchodu.csv"
    },
    {
      "tabularUrl": "https://data.cssz.cz/dump/zpusob-vyplat-duchodu-v-cr.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/rozpočet_běžné_výdaje_2016_MMO.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/bezne-vydaje-rozpoctu-mmo/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/rozpočet_kapitálové_výdaje_2016_MMO.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/kapitalove-vydaje-rozpoctu-mmo/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A321-UK01-Počet-případů-dle-stavu-v-jednotlivých-měsíc-1.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/koordinovana-zavazna-stanoviska_2017/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/mista-ktera-doporucim-navstevnikum-odjinud/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/pocitova_mapa_2016_q2.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A472-UK04-Počet-a-typ-popelnic-v-jednotlivých-městských-obvodech.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocet-a-typ-sbernych-nadob/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocet-dopravnich-prestupku-dle-zpusobu-reseni-evidovanych-v-roce-2017/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A190-UK01-Počet-přestupků-dle-druhu-řešení-blokové-domluva_2017.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocet-dopravnich-prestupku-dle-zpusobu-reseni-evidovanych-v-roce-2018/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A190-UK01-Počet-přestupků-dle-druhu-řešení-blokové-domluva-1.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocet-dopravnich-prestupku-v-jednotlivych-mesicich-2018/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A190-UK04-Počet-přestupků-v-jednotlivých-měsících-1.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A190-UK04-Počet-přestupků-v-jednotlivých-měsících_2017.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocet-dopravnich-prestupku-v-jednotlivych-mesicich_2017/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocet-evidovanych-psu/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A045-UK10-Časový-vývoj-aktuálního-počtu-psů-s-čipem.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocet-objednavek-dle-agend/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A731-UK04-Počet-objednávek-dle-jednotlivých-agend.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocet-objednavek-dle-cinnosti/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A731-UK05-Počet-objednávek-dle-činnosti.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A210-UK01A-Počet-petic.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocet-petic/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocet-podanych-petic-v-roce-2017/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A210-UK01A-Počet-petic-1.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A331-UK22-Počet-podaných-žádostí-o-vydání-OP-bez-strojově.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocet-podanych-zadosti-blesk/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocet-podanych-zadosti-o-cd/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A340-UK05aa-Počet-přijatých-žádostí-o-vydání-cestovní.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A331-UK21a-Počet-podaných-žádostí-o-vydání-elektronického-OP.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocet-podanych-zadosti-o-op/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocet-prestupku-dle-druhu-vyreseni/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/2016/08/A190-UK01-Počet-přestupků-dle-druhu-řešení-1.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/2016/08/A190-UK04-Počet-přestupků-v-jednotlivých-měsících.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocet-prestupku-v-mesicich/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocet-pripadu-dle-stavu/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A321-UK01-Počet-případů-dle-stavu-v-jednotlivých-měsíc.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A045-UK01-Počet-psů-s-čipem.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocet-psu-oznacenych-cipem/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A045-UK02-Počet-psů-s-tetováním.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocet-psu-tetovanim/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A271-UK01-Počet-kontrol-dle-typu-v-jednotlivých-městských-obvodec.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocet-stavebnich-kontrol/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A722-UK05A-Počet-uživatelů-systému.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocet-uzivatelu-systemu-emia/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocitova-mapa-horka-mista-ktera-by-se-mela-rozvijet-aby-byla-behem-horka-prijemnejsi/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/Pocitova_mapa_horka_Q3.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/Pocitova_mapa_horka_Q4.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocitova-mapa-horka-necitim-prijemne/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/Pocitova_mapa_horka_Q2.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocitova-mapa-horka-negativne-hodnocene-lokality/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/Pocitova_mapa_horka_Q1.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocitova-mapa-horka-pozitivne-hodnocene-lokality/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocitova-mapa-mista-kde-by-se-mesto-melo-rozvijet/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/pocitova_mapa_2016_q3.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/pocitova_mapa_2016_q4.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocitova-mapa-mista-kde-se-necitim-fajnove/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/pocitova-mapa-misto-kde-se-citim-fajnove/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/pocitova_mapa_2016_q1.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/prehled-nalezu-evidovanych-smo/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/ztráty_a_nálezy-1.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/rozpočet_příjmy_2016_MMO.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/prijmy-dle-polozky-mmo/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/program-narodniho-divadla-moravskoslezskeho/metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/turisticke-cile/dokumentace_datoveho_schematu_pro_format.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/provozovny-organizace-sareza/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/2016/11/csv_jednotná_šablona_organizace_provozovny_Sareza.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/rozpocet-2017-bezne-vydaje/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/Rozpočet_běžné_výdaje_2017_MMO.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/rozpocet-2017-kapitalove-vydaje/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/Rozpočet_kapitálové_výdaje_2017_MMO.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/Rozpočet_příjmy_2017_MMO.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/rozpocet-2017-prijmy/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/seznam-socialnich-sluzeb/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/seznam_sociálních_služeb.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/seznam-uradu-a-organizaci/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/2016/10/seznam_organizaci_SMO.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/export-statistiky-poctu-prujezdu-2017.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/statistika-prujezdu-vozidel-ze-sledovanych-krizovatek-v-roce-2017/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/statistika-prujezdu-vozidel/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/export-statistiky-poctu-prujezdu-2016.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/stavebni-dozor-stavebniho-uradu-pocet-kontrol-v-roce-2017/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A271-UK01-Počet-kontrol-dle-typu-v-jednotlivých-městských-obvodec-1.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A271-UK01-Počet-kontrol-dle-typu-v-jednotlivých-městských-obvodec-1.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/stavebni-dozor-stavebniho-uradu-pocet-kontrol-v-roce-2018/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A210-UK01-Počet-podání-dle-typu-1.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/stiznosti-a-podnety-pocet-podani-v-roce-2017/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/stiznosti-petice-a-podnety/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/A210-UK01-Počet-podání-dle-typu.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/turisticke-cile/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/Turisticke_cile.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/wp-content/uploads/hřiště.csv"
    },
    {
      "tabularUrl": "https://opendata.ostrava.cz/api/3/action/data/verejna-hriste/strojove_citelne_metadata_datove_sady.csv"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/401"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/6"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/71"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/73"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/72"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/4"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/29"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/16"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/15"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/12"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/11"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/91"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/46"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/40"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/43"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/41"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/42"
    },
    {
      "tabularUrl": "http://uradbezcekani.cz/opendata/data-pd-jizdecka.php"
    },
    {
      "tabularUrl": "http://uradbezcekani.cz/opendata/data-pd-rychtarka.php"
    },
    {
      "tabularUrl": "http://uradbezcekani.cz/opendata/data-plznito.php"
    },
    {
      "tabularUrl": "http://uradbezcekani.cz/opendata/data-orvr.php"
    },
    {
      "tabularUrl": "http://uradbezcekani.cz/opendata/data-opcd.php"
    },
    {
      "tabularUrl": "http://uradbezcekani.cz/opendata/data-zu.php"
    },
    {
      "tabularUrl": "http://www.vlada.cz/assets/urad-vlady/otevrena_data/Centralni_evidence/CEA_programy-2015-2017.csv"
    },
    {
      "tabularUrl": "https://www.vlada.cz/assets/urad-vlady/otevrena_data/clenove_poradnich_organu/seznam_clenu.csv"
    },
    {
      "tabularUrl": "https://www.vlada.cz/assets/urad-vlady/otevrena_data/seznam_certifikatoru/seznam_certifikatoru.csv"
    },
    {
      "tabularUrl": "https://www.vlada.cz/assets/urad-vlady/otevrena_data/Vypracovane_analyticke_statisticke_a_strategicke_dokumenty/analyticke_dokumenty.csv"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/37caeb60-c1d8-4c1e-80ed-8e2e1ff5035d/resource/4e1c5c07-907d-455f-ac0d-f3fe480e7742/download/45920762-07ba-484b-a3ff-c7f3ff76638d-pageviews.h8p3ms8i.csv"
    },
    {
      "tabularUrl": "https://www.czechtourism.cz/getmedia/13185630-662d-4a5a-b516-7966348bc490/CzT_faktury_2019.csv.aspx"
    },
    {
      "tabularUrl": "https://data.army.cz/sites/default/files/NEN%20k%2031.3.2019%20zve%C5%99..csv"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/563"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/566"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/564"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/565"
    },
    {
      "tabularUrl": "http://soubor.plzen.eu/gis/sumare/567"
    },
    {
      "tabularUrl": "http://opendata.praha.eu/dataset/a6176834-d333-4f27-8871-1d7112121f44/resource/ed0a09c1-9290-455d-baa9-08b4197445b8/download/c8505f5f-233f-477f-89f1-e2a9c4d9bef3-kdf-2018.csv"
    },
    {
      "tabularUrl": "https://www.czso.cz/documents/62353418/108731174/060003-19data032919.csv"
    }
  ]
}'