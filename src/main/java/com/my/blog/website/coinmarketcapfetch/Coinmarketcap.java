//package com.my.blog.website.coinmarketcap;
//
//
//import com.alibaba.fastjson.JSON;
//import com.my.blog.website.coinmarketcapfetch.entity.ListCoinInfo;
//import org.htmlcleaner.CleanerProperties;
//import org.htmlcleaner.DomSerializer;
//import org.htmlcleaner.HtmlCleaner;
//import org.htmlcleaner.TagNode;
//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.w3c.dom.Document;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//import javax.xml.xpath.XPath;
//import javax.xml.xpath.XPathConstants;
//import javax.xml.xpath.XPathFactory;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
////import java.util.LinkedHashMap;
////import java.util.Map;
//
////https://www.jianshu.com/p/5f9492e81198
//public class Coinmarketcap {
//
//    public static Object fecthNode(String url, String xpath) throws Exception {
//        String html = null;
//        try {
//            Connection connect = Jsoup.connect(url);
//            html = connect.get().body().html();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        HtmlCleaner hc = new HtmlCleaner();
//        TagNode tn = hc.clean(html);
//        Document dom = new DomSerializer(new CleanerProperties()).createDOM(tn);
//        XPath xPath = XPathFactory.newInstance().newXPath();
//
//        Object result = xPath.evaluate(xpath, dom, XPathConstants.NODESET);
//
//        return result;
//    }
//
//    // 获取xpath下的a标签的文本值及href属性值
//    //**
//    public static Map<String, String> fecthByMap(String url, String xpath) throws Exception {
//
//        List<ListCoinInfo> listCoinInfos = new ArrayList<>();
//
//        Map<String, String> nodeMap = new LinkedHashMap<>();
//        Object result = fecthNode(url, xpath);
//
//        if (result instanceof NodeList) {
//
//            ListCoinInfo coinInfo = new ListCoinInfo();
//
//            NodeList nodeList = (NodeList) result;
//            for (int i = 0; i < nodeList.getLength(); i++) {
//
//                Node node = nodeList.item(i);
//                if (node == null) {
//                    continue;
//                }
////                System.out.println(node.getNodeValue());
////                nodeMap.put(node.getTextContent(), node.getAttributes().getNamedItem("href") != null ?
////                        node.getAttributes().getNamedItem("href").getTextContent() : "");
////                System.out.println(node.getTextContent() + " : " + node.getAttributes().getNamedItem("href"));
////                System.out.println(node.getTextContent());
//                String trContent = node.getTextContent().replace("*", "");
////                System.out.println("行的内容： " + trContent);
//                String[] trContentArr = trContent.split(" ");
//                System.out.println("trContentArr大小： " + trContentArr.length);
////                for (int index = 0; index < trContentArr.length; index++) {
////                    System.out.println(index + " " + trContentArr[index]);
////                }
//
//                if (trContentArr.length == 234 || trContentArr.length == 235) {
//                    coinInfo.setName(trContentArr[26]);
//                    coinInfo.setMarketCap(trContentArr[38]);
//                    coinInfo.setPrice(trContentArr[50]);
//                    coinInfo.setVolume24h(trContentArr[62]);
//                    coinInfo.setCirculatingSupply(trContentArr[75]);
//                    if (trContentArr.length == 234 ) {
//                        coinInfo.setChange24h(trContentArr[88]);
//                    } else {
//                        coinInfo.setChange24h(trContentArr[89]);
//                    }
//
//                } else if (trContentArr.length == 236) {
//                    String name = trContentArr[26] + " " + trContentArr[27];
//                    coinInfo.setName(name);
//                    coinInfo.setMarketCap(trContentArr[38 + 1]);
//                    coinInfo.setPrice(trContentArr[50 + 1]);
//                    coinInfo.setVolume24h(trContentArr[62 + 1]);
//                    coinInfo.setCirculatingSupply(trContentArr[75 + 1]);
//                    coinInfo.setChange24h(trContentArr[90]);
//                }
//                listCoinInfos.add(coinInfo);
//                System.out.println("=========================== coinInfo ： " + JSON.toJSONString(coinInfo));
//
//                System.out.println("=========================== 总记录数 ： " + i);
//                //TODO 结果还是又问题，等待修改
//            }
//        }
//
//        return nodeMap;
//    }
//
//    public static void main(String[] args) throws Exception {
//
////        TODO OK
////        fecthByMap("https://coinmarketcap.com/1", "//div[@class='container main-section']");
//
////        fecthByMap("https://coinmarketcap.com/1", "//div[@class='table-fixed-column-mobile compact-name-column']");
//
////        fecthByMap("https://coinmarketcap.com/1", "//td[@class='no-wrap currency-name']");
//        fecthByMap("https://coinmarketcap.com/1", "//div[@class='table-fixed-column-mobile compact-name-column']//table[@id='currencies']//tbody//tr");
//
//    }
//
//}
