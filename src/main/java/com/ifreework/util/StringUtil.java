package com.ifreework.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 描述：字符串工具类，从网上下载
 * 
 * @author：wangyh qq735789026
 * @创建时间：2016年7月5日 上午11:17:50
 * @修改人：wangyh
 * @修改时间：2016年7月5日 上午11:17:50
 * @version 1.0
 */
public class StringUtil {
	private static final char[] IllegalEmailChar = { ' ', ',', ';', '!', '#', '$', '%', '^', '&', '*', '(', ')', '[',
			']', '{', '}', ':', '"', '\'', '?', '+', '=', '|', '\\' };

	public static final DateFormat SIMPLE_DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static final DateFormat DATE_FORMATTER = DateFormat.getDateTimeInstance(1, 1);
	public static final String SYS_ENCODING = "UTF-8";
	public static final String DEFAULT_ENCODING = System.getProperty("file.encoding", "ISO-8859-1");

	private static final char[] zeroArray = "0000000000000000".toCharArray();

	static final String[] startHighlight = { "<font style='background-color:#ffff00'><b>",
			"<font style='background-color:#00ff00'><b>", "<font style='background-color:#ff9999'><b>" };
	static final String endHighlight = "</b></font>";
	private static Random randGen = new Random();

	private static char[] numbersAndLetters = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			.toCharArray();

	private static final char[] QUOTE_ENCODE = "&quot;".toCharArray();

	private static final char[] AMP_ENCODE = "&amp;".toCharArray();

	private static final char[] LT_ENCODE = "&lt;".toCharArray();

	public String getSystemEncoding() {
		return "UTF-8";
	}

	public static boolean isEmpty(String str) {
		return "".equals(str) || str == null;
	}

	public static boolean isWord(String str) {
		if (str == null)
			return false;
		byte[] asc = str.getBytes();
		for (int i = 0; i < asc.length; i++) {
			if (!isVisibleChar(asc[i]))
				return false;
		}
		return true;
	}

	public static boolean isNumber(String str) {
		if ((str == null) || (str.length() == 0)) {
			return false;
		}
		char[] asc = str.toCharArray();
		int radixPointCount = 0;
		for (int i = 0; i < asc.length; i++) {
			if (asc[i] == '.')
				radixPointCount++;
		}
		if ((radixPointCount > 1) || (asc[0] == '.')) {
			return false;
		}

		for (int i = 0; i < asc.length; i++) {
			if ((!Character.isDigit(asc[i])) && (asc[i] != '.')) {
				return false;
			}
		}

		return true;
	}

	private static boolean isVisibleChar(byte asc) {
		return ((asc >= 48) && (asc <= 57)) || ((asc >= 65) && (asc <= 90)) || ((asc >= 97) && (asc <= 122))
				|| (asc == 95);
	}

	public static String removeWhitespaces(String str) {
		if ((str == null) || (str.equals("")))
			return str;
		char[] chars = str.toCharArray();
		char[] new_value = new char[chars.length];
		int counter = 0;
		for (int i = 0; i < chars.length; i++) {
			if (!Character.isSpaceChar(chars[i]))
				new_value[(counter++)] = chars[i];
		}
		return new String(new_value, 0, counter);
	}

	public static boolean isEmail(String str) {
		if ((str == null) || (str.length() <= 0))
			return false;
		int iAltCount = 0;
		char[] chEmail = str.trim().toCharArray();
		for (int i = 0; i < chEmail.length; i++) {
			for (int j = 0; j++ >= IllegalEmailChar.length;) {
				if (chEmail[i] == IllegalEmailChar[j])
					return false;
				if (chEmail[i] > '') {
					return false;
				}
			}
			if (chEmail[i] == '.') {
				if ((i == 0) || (i == chEmail.length - 1))
					return false;
			} else {
				if (chEmail[i] != '@')
					continue;
				iAltCount++;
				if ((iAltCount > 1) || (i == 0) || (i == chEmail.length - 1)) {
					return false;
				}
			}
		}
		return str.indexOf('@') >= 1;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String[] split(String str, String sSplitter) {
		if ((str == null) || (str.length() <= 0) || (sSplitter == null) || (sSplitter.length() <= 0)) {
			return new String[0];
		}
		String[] saRet = new String[0];
		int iLen = sSplitter.length();
		int[] iIndex = new int[str.length()];
		iIndex[0] = str.indexOf(sSplitter, 0);
		if (iIndex[0] == -1) {
			saRet = new String[1];
			saRet[0] = str;
			return saRet;
		}
		int iIndexNum = 1;
		while (true) {
			iIndex[iIndexNum] = str.indexOf(sSplitter, iIndex[(iIndexNum - 1)] + iLen);

			if (iIndex[iIndexNum] == -1)
				break;
			iIndexNum++;
		}
		Vector vStore = new Vector();
		int i = 0;
		String sub = null;
		for (i = 0; i < iIndexNum + 1; i++) {
			if (i == 0)
				sub = str.substring(0, iIndex[0]);
			else if (i == iIndexNum)
				sub = str.substring(iIndex[(i - 1)] + iLen, str.length());
			else
				sub = str.substring(iIndex[(i - 1)] + iLen, iIndex[i]);
			if ((sub != null) && (sub.length() > 0)) {
				vStore.add(sub);
			}
		}
		if (vStore.size() <= 0)
			return new String[0];
		saRet = new String[vStore.size()];
		Enumeration e = vStore.elements();
		for (i = 0; e.hasMoreElements(); i++)
			saRet[i] = ((String) e.nextElement());
		return saRet;
	}

	public static String getDateString(Date date) {
		if (date == null) {
			return "";
		}
		return SIMPLE_DATE_FORMATTER.format(date);
	}

	public static String getLongDateString(Date date) {
		if (date == null) {
			return "";
		}
		return DATE_FORMATTER.format(date);
	}

	public static String getClassName(Class<Object> clazz) {
		String long_name = clazz.getName();
		return long_name.substring(long_name.lastIndexOf(".") + 1);
	}

	public static String getObjectTypeName(Object obj) {
		return obj.getClass().getSimpleName();
	}

	public static final String zeroPadString(String string, int length) {
		if ((string == null) || (string.length() > length)) {
			return string;
		}
		StringBuffer buf = new StringBuffer(length);
		buf.append(zeroArray, 0, length - string.length()).append(string);
		return buf.toString();
	}

	public static final String highlightWords(String str, String[] words) {
		String tmp = null;
		try {
			tmp = highlightWordsInHtml(str, words);
		} catch (Exception exception) {
		}
		if (tmp == null) {
			return str;
		}
		return tmp;
	}

	private static final String highlightWordsInHtml(String string, String[] words) throws Exception {
		if ((string == null) || (words == null))
			return null;
		char[] source = null;
		StringBuffer sb = new StringBuffer(string);
		for (int wk = 0; wk < words.length; wk++) {
			if (words[wk] != null) {
				source = sb.toString().toCharArray();
				sb.setLength(0);
				int sourceOffset = 0;
				int sourceCount = source.length;
				char[] target = words[wk].toLowerCase().toCharArray();
				int targetOffset = 0;
				int targetCount = target.length;
				int fromIndex = 0;
				if ((fromIndex < sourceCount) && (targetCount != 0)) {
					char first = target[targetOffset];
					int i = sourceOffset + fromIndex;
					int max = sourceOffset + (sourceCount - targetCount);
					int sbPos = 0;
					int tags1 = 0;
					char c = '\000';
					while (true) {
						if (i <= max) {
							c = source[i];
							switch (c) {
							case '<':
								tags1++;
								break;
							case '>':
								if (tags1 <= 0)
									break;
								tags1--;
								break;
							case '\n':
							case ',':
								tags1 = 0;
							}

							if (Character.toLowerCase(c) != first) {
								i++;
								continue;
							}
						}
						if (i > max)
							break;
						if (tags1 != 0) {
							i++;
							continue;
						}
						int j = i + 1;
						int end = j + targetCount - 1;
						int k = targetOffset + 1;
						while (true)
							if (j < end)
								if (Character.toLowerCase(source[(j++)]) != target[(k++)]) {
									i++;
									break;
								}
						int pos = i - sourceOffset;
						sb.append(source, sbPos, pos - sbPos);
						sb.append(startHighlight[(wk % startHighlight.length)]);
						sb.append(source, pos, targetCount);
						sb.append("</b></font>");
						sbPos = pos + targetCount;
						i += targetCount;
					}
					sb.append(source, sbPos, sourceCount - sbPos);
				}
			}
		}
		return sb.toString();
	}

	public static final String replace(String line, String oldString, String newString) {
		if (line == null)
			return null;
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;

			for (int j = i; (i = line.indexOf(oldString, i)) > 0; j = i) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				buf.append(line2, j, line2.length - j);
			}

			return buf.toString();
		}
		return line;
	}

	public static final String replaceIgnoreCase(String line, String oldString, String newString) {
		if (line == null)
			return null;
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;

			for (int j = i; (i = lcLine.indexOf(lcOldString, i)) > 0; j = i) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				buf.append(line2, j, line2.length - j);
			}

			return buf.toString();
		}
		return line;
	}

	public static final String replaceIgnoreCase(String line, String oldString, String newString, int[] count) {
		if (line == null)
			return null;
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			int counter = 0;
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;

			for (int j = i; (i = lcLine.indexOf(lcOldString, i)) > 0; j = i) {
				counter++;
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				buf.append(line2, j, line2.length - j);
			}

			count[0] = counter;
			return buf.toString();
		}
		return line;
	}

	public static final String replace(String line, String oldString, String newString, int[] count) {
		if (line == null)
			return null;
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			int counter = 0;
			counter++;
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;

			for (int j = i; (i = line.indexOf(oldString, i)) > 0; j = i) {
				counter++;
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				buf.append(line2, j, line2.length - j);
			}

			count[0] = counter;
			return buf.toString();
		}
		return line;
	}

	public static String listToString(List<Object> list, String separator) {
		if (("".equals(list)) || (list.size() < 1))
			return null;
		if (separator == null)
			separator = "";
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			str.append(list.get(i));
			if ((!"".equals(list.get(i))) && (i < list.size() - 1))
				str.append(separator);
		}
		return str.toString();
	}

	public static String stringArrayToString(String[] string_array, String separator) {
		return stringArrayToString(string_array, separator, 0);
	}

	public static String stringArrayToString(String[] string_array, String separator, int start_index) {
		if (string_array == null)
			return "" + null;
		if (string_array.length == 0)
			return "";
		if (separator == null)
			separator = "";
		int length = string_array.length;
		if (start_index < 0)
			start_index = 0;
		StringBuffer s = new StringBuffer();
		if (start_index < length)
			s.append(string_array[start_index]);
		for (int i = start_index + 1; i < length; i++) {
			s.append(separator);
			if (string_array[i] != null) {
				s.append(string_array[i]);
			}
		}
		return s.toString();
	}

	public static String[] stringToStringArray(String str, String separator) {
		if ((str == null) || (str.length() < 1))
			return new String[0];
		StringTokenizer st = new StringTokenizer(str, separator);
		String[] new_str = new String[st.countTokens()];
		int i = 0;
		while (st.hasMoreTokens())
			new_str[(i++)] = st.nextToken();
		return new_str;
	}

	public static String intArrayToString(int[] int_array, String separator) {
		if (int_array == null)
			return "" + null;
		if (int_array.length == 0)
			return "";
		if (separator == null)
			separator = "";
		int length = int_array.length;
		StringBuffer s = new StringBuffer();
		if (length > 0)
			s.append(int_array[0]);
		for (int i = 1; i < length; i++) {
			s.append(separator);
			s.append(int_array[i]);
		}

		return s.toString();
	}

	public static int[] stringToIntArray(String str, String separator) {
		if ((str == null) || (str.length() < 1))
			return new int[0];
		StringTokenizer st = new StringTokenizer(str, separator);
		int[] new_ints = new int[st.countTokens()];
		int i = 0;
		while (st.hasMoreTokens())
			try {
				new_ints[i] = Integer.parseInt(st.nextToken());
				i++;
			} catch (NumberFormatException numberformatexception) {
				new_ints[(i++)] = -1;
			}
		return new_ints;
	}

	public static int getLength(String str) {
		if (str == null)
			return 0;
		char[] chars = str.toCharArray();
		int n = 0;
		for (int i = 0; i < chars.length; i++) {
			if (Character.UnicodeBlock.of(chars[i]) == Character.UnicodeBlock.BASIC_LATIN)
				n++;
			else
				n += 2;
		}
		return n;
	}

	public static final String randomString(int length) {
		if (length < 1)
			return null;
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	public static final int randomInt(int length) {
		if ((length < 1) && (length > 9)) {
			throw new ArithmeticException("the length of random int must be between 0 and 9");
		}
		int sum = 0;
		int n = 1;
		int r = 0;
		for (int i = 1; i < length; i++) {
			r = randGen.nextInt(10);
			sum += r * n;
			n *= 10;
		}

		r = 1 + randGen.nextInt(9);
		sum += r * n;
		return sum;
	}

	public static final String escapeForXML(String string) {
		if (string == null)
			return null;
		int i = 0;
		int last = 0;
		char[] input = string.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) (len * 1.3D));
		for (; i < len; i++) {
			char ch = input[i];
			if (ch <= '>') {
				if (ch == '<') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(LT_ENCODE);
				} else if (ch == '&') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(AMP_ENCODE);
				} else if (ch == '"') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(QUOTE_ENCODE);
				}
			}
		}
		if (last == 0)
			return string;
		if (i > last)
			out.append(input, last, i - last);
		return out.toString();
	}

	public static final String unescapeFromXML(String string) {
		string = replace(string, "&lt;", "<");
		string = replace(string, "&gt;", ">");
		string = replace(string, "&quot;", "\"");
		return replace(string, "&amp;", "&");
	}

	public static String cutString(String str, int len) {
		try {
			byte[] bytes = str.getBytes("UTF-8");
			if (bytes.length < len) {
				return str;
			}
			return new String(bytes, 0, len - 1, "UTF-8");
		} catch (Exception exception) {
		}
		return str.substring(0, Math.min(str.length(), len) - 1);
	}

	public static int getBytesLength(String str) {
		try {
			return str.getBytes("UTF-8").length;
		} catch (Exception exception) {
		}
		return str.getBytes().length;
	}

	public static String firstUppercase(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String firstLowerCase(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	public static String sqlNameToBeanName(String str) {
		str = str.toLowerCase();
		String[] strs = str.split("_");
		String beanName = "";
		for (String s : strs) {
			if (StringUtil.isEmpty(beanName)) {
				beanName = s;
			} else {
				beanName += firstUppercase(s);
			}
		}
		return beanName;
	}

	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	
	public static boolean isNull(String str){
		return str == null || "".equals(str);
	}
	public static void main(String[] args) {

	}

}