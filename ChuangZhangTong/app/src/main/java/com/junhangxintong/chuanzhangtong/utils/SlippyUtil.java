package com.junhangxintong.chuanzhangtong.utils;

/**
 * @Description:根据经纬度计算出瓦片图x和y坐标值
 * @author gh.peng
 * @time:2017年9月28日 下午6:14:00
 */
public class SlippyUtil {

	public static void main(String[] args) {
		int zoom = 16;// 瓦片图显示层级，取值范围:1至16
		double lat = 38.92d;// 纬度
		double lon = 121.62d;
		// System.out.println("http://tile.openstreetmap.org/" + getTileNumber(lat, lon, zoom) + ".png");
		System.out.println("http://www.hifleet.com/dgetworldtiles.do?" + getTileNumber(lat, lon, zoom));
	}

	/**
	 * @Description:根据经纬度计算出瓦片图x和y坐标值
	 * @param lat 纬度
	 * @param lon 经度
	 * @param zoom 瓦片图显示层级，取值范围:1至16
	 * @return
	 * 		String
	 * @author: gh.peng
	 * @time:2017年10月9日 下午3:15:32
	 */
	public static String getTileNumber(double lat, double lon, int zoom) {
		int xtile = (int) Math.floor((lon + 180) / 360 * (1 << zoom));
		int ytile = (int) Math
				.floor((1 - Math.log(Math.tan(Math.toRadians(lat)) + 1 / Math.cos(Math.toRadians(lat))) / Math.PI) / 2
						* (1 << zoom));
		if (xtile < 0) {
			xtile = 0;
		}
		if (xtile >= (1 << zoom)) {
			xtile = ((1 << zoom) - 1);
		}
		if (ytile < 0) {
			ytile = 0;
		}
		if (ytile >= (1 << zoom)) {
			ytile = ((1 << zoom) - 1);
		}
		// return ("" + zoom + "/" + xtile + "/" + ytile);
		return ("z=" + zoom + "&x=" + xtile + "&y=" + ytile
				+ "&usertoken=UVKv6uPfGdkIqB5YGejw159t/NICPap9EJeFkkx2YVrygkzeWNR2oF+cNlWr418p");

	}

}
