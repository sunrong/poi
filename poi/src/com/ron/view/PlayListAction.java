package com.ron.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ron.Command;
import com.ron.dao.DAOFactory;
import com.ron.dao.PlayListDAO;

public class PlayListAction extends Command {

	@Override
	public String list2() {
		// TODO Auto-generated method stub
		String result = "[ { \"name\": \"Kitchen Sink\", \"thumb\": \"sink.png\", \"url\": \"kitchensink\", \"type\": \"Application\" }, { \"name\": \"Twitter app\", \"thumb\": \"twitter.png\", \"url\": \"twitter\", \"type\": \"Application\" }, { \"name\": \"Kiva app\", \"thumb\": \"kiva.png\", \"url\": \"kiva\", \"type\": \"Application\" }, { \"name\": \"Geocongress\", \"thumb\": \"geocongress.png\", \"url\": \"geocongress\", \"type\": \"Application\" }, { \"name\": \"AJAX\", \"thumb\": \"ajax.png\", \"url\": \"ajax\", \"type\": \"Example\" }, { \"name\": \"Carousel\", \"thumb\": \"carousel.png\", \"url\": \"carousel\", \"type\": \"Example\" }, { \"name\": \"Drag &amp; Drop\", \"thumb\": \"DnD.png\", \"url\": \"dragdrop\", \"type\": \"Example\" }, { \"name\": \"Forms\", \"thumb\": \"forms.png\", \"url\": \"forms\", \"type\": \"Example\" }, { \"name\": \"Guide\", \"thumb\": \"guide.png\", \"url\": \"guide\", \"type\": \"Example\" }, { \"name\": \"Icons\", \"thumb\": \"icons.png\", \"url\": \"icons\", \"type\": \"Example\" }, { \"name\": \"Map\", \"thumb\": \"map.png\", \"url\": \"map\", \"type\": \"Example\" }, { \"name\": \"Nested List\", \"thumb\": \"nestedList.png\", \"url\": \"nestedlist\", \"type\": \"Example\" }, { \"name\": \"Overlays\", \"thumb\": \"overlays.png\", \"url\": \"overlays\", \"type\": \"Example\" }, { \"name\": \"Picker\", \"thumb\": \"picker.png\", \"url\": \"picker\", \"type\": \"Example\" }, { \"name\": \"Sortable\", \"thumb\": \"sortable.png\", \"url\": \"sortable\", \"type\": \"Example\" }, { \"name\": \"Tabs\", \"thumb\": \"tabs.png\", \"url\": \"tabs\", \"type\": \"Example\" }, { \"name\": \"Tabs 2\", \"thumb\": \"tabs2.png\", \"url\": \"tabs2\", \"type\": \"Example\" }, { \"name\": \"Toolbars\", \"thumb\": \"toolbars.png\", \"url\": \"toolbars\", \"type\": \"Example\" }, { \"name\": \"YQL\", \"thumb\": \"yql.png\", \"url\": \"yql\", \"type\": \"Example\" }]";
		return result;
	}

	@Override
	public String list(){
//		String result = "[ { \"name\": \"Kitchen Sink\", \"thumb\": \"sink.png\", \"url\": \"kitchensink\", \"type\": \"Application\" }, { \"name\": \"Twitter app\", \"thumb\": \"twitter.png\", \"url\": \"twitter\", \"type\": \"Application\" }, { \"name\": \"Kiva app\", \"thumb\": \"kiva.png\", \"url\": \"kiva\", \"type\": \"Application\" }, { \"name\": \"Geocongress\", \"thumb\": \"geocongress.png\", \"url\": \"geocongress\", \"type\": \"Application\" }, { \"name\": \"AJAX\", \"thumb\": \"ajax.png\", \"url\": \"ajax\", \"type\": \"Example\" }, { \"name\": \"Carousel\", \"thumb\": \"carousel.png\", \"url\": \"carousel\", \"type\": \"Example\" }, { \"name\": \"Drag &amp; Drop\", \"thumb\": \"DnD.png\", \"url\": \"dragdrop\", \"type\": \"Example\" }, { \"name\": \"Forms\", \"thumb\": \"forms.png\", \"url\": \"forms\", \"type\": \"Example\" }, { \"name\": \"Guide\", \"thumb\": \"guide.png\", \"url\": \"guide\", \"type\": \"Example\" }, { \"name\": \"Icons\", \"thumb\": \"icons.png\", \"url\": \"icons\", \"type\": \"Example\" }, { \"name\": \"Map\", \"thumb\": \"map.png\", \"url\": \"map\", \"type\": \"Example\" }, { \"name\": \"Nested List\", \"thumb\": \"nestedList.png\", \"url\": \"nestedlist\", \"type\": \"Example\" }, { \"name\": \"Overlays\", \"thumb\": \"overlays.png\", \"url\": \"overlays\", \"type\": \"Example\" }, { \"name\": \"Picker\", \"thumb\": \"picker.png\", \"url\": \"picker\", \"type\": \"Example\" }, { \"name\": \"Sortable\", \"thumb\": \"sortable.png\", \"url\": \"sortable\", \"type\": \"Example\" }, { \"name\": \"Tabs\", \"thumb\": \"tabs.png\", \"url\": \"tabs\", \"type\": \"Example\" }, { \"name\": \"Tabs 2\", \"thumb\": \"tabs2.png\", \"url\": \"tabs2\", \"type\": \"Example\" }, { \"name\": \"Toolbars\", \"thumb\": \"toolbars.png\", \"url\": \"toolbars\", \"type\": \"Example\" }, { \"name\": \"YQL\", \"thumb\": \"yql.png\", \"url\": \"yql\", \"type\": \"Example\" }]";
		
        PlayListDAO playListDAO = DAOFactory.getInstance().getDAOImpl(PlayListDAO.class);
        List list = playListDAO.list();
        
		return TellFront(list).toString();
	}
	
	private JSONObject TellFront(List list){
		
        JSONArray ja = JSONArray.fromObject(list);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        m.put("count", list.size());
        m.put("user", ja);
        m.put("message", " " + list.size() + " ");
        
        return JSONObject.fromObject(m);
	}
	
}
