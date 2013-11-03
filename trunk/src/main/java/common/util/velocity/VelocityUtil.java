package common.util.velocity;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import common.util.datetime.DateTimeUtil;

public class VelocityUtil {

	public static String objectToTemplate(Object object, String vmPath) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();

		Template t = ve.getTemplate(vmPath);

		VelocityContext context = new VelocityContext();
		context.put("object", object);
		context.put("DateTimeUtil", DateTimeUtil.class);

		StringWriter writer = new StringWriter();
		t.merge(context, writer);

		return writer.toString();
	}
}
