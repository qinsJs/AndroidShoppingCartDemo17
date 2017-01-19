package walden.shoppingcart.demo;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by next on 17-1-19.
 */

public class A
{

	public static String getJson(Context c, String fileName)
	{
		String result = null;
		try
		{
			InputStream is = c.getAssets().open(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuffer sb = new StringBuffer();
			String cache;
			while ((cache = br.readLine()) != null)
			{
				sb.append(cache);
			}
			return sb.toString();
		} catch (Exception e)
		{
		}
		return result;
	}
}
