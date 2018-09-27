package cn.codeyang.core.social.github.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component("connect/status")
public class YangConnectionStatusView extends AbstractView {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //Map<String, List<Connection<?>>> connections =
        Map<String, List<Connection<?>>> connectionMap = (Map<String, List<Connection<?>>>) model.get("connectionMap");
        Set<String> providerIds = (Set<String>) model.get("providerIds");

        Map<String, Boolean> result = new HashMap<>();
        for (String key : connectionMap.keySet()) {
            result.put(key, CollectionUtils.isNotEmpty(connectionMap.get(key)));
        }

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
