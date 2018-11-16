package com.maxcar.base.util.wechat;
/** 
 * 接收到的微信xml实体类 
 * @author yangshujun
 * 
 */  
public class ReceiveXmlEntity {  
    private String ToUserName="";  
    private String FromUserName="";  
    private String CreateTime="";  
    private String MsgType="";  
    private String MsgId="";  
    private String Event="";  
    private String EventKey="";  
    private String Ticket="";  
    private String Latitude="";  
    private String Longitude="";  
    private String Precision="";  
    private String PicUrl="";  
    private String MediaId="";  
    private String Title="";  
    private String Description="";  
    private String Url="";  
    private String Location_X="";  
    private String Location_Y="";  
    private String Scale="";  
    private String Label="";  
    private String Content="";  
    private String Format="";  
    private String Recognition=""; 
    private String MenuId="";
    private String Encrypt="";
    
    public String getEncrypt() {
		return Encrypt;
	}
	public void setEncrypt(String encrypt) {
		Encrypt = encrypt;
	}
	public String getMenuId() {
		return MenuId;
	}
	public void setMenuId(String menuId) {
		MenuId = menuId;
	}
	public String getRecognition() {  
        return Recognition;  
    }  
    public void setRecognition(String recognition) {  
        Recognition = recognition;  
    }  
    public String getFormat() {  
        return Format;  
    }  
    public void setFormat(String format) {  
        Format = format;  
    }  
    public String getContent() {  
        return Content;  
    }  
    public void setContent(String content) {  
        Content = content;  
    }  
    public String getLocation_X() {  
        return Location_X;  
    }  
    public void setLocation_X(String locationX) {  
        Location_X = locationX;  
    }  
    public String getLocation_Y() {  
        return Location_Y;  
    }  
    public void setLocation_Y(String locationY) {  
        Location_Y = locationY;  
    }  
    public String getScale() {  
        return Scale;  
    }  
    public void setScale(String scale) {  
        Scale = scale;  
    }  
    public String getLabel() {  
        return Label;  
    }  
    public void setLabel(String label) {  
        Label = label;  
    }  
    public String getTitle() {  
        return Title;  
    }  
    public void setTitle(String title) {  
        Title = title;  
    }  
    public String getDescription() {  
        return Description;  
    }  
    public void setDescription(String description) {  
        Description = description;  
    }  
    public String getUrl() {  
        return Url;  
    }  
    public void setUrl(String url) {  
        Url = url;  
    }  
    public String getPicUrl() {  
        return PicUrl;  
    }  
    public void setPicUrl(String picUrl) {  
        PicUrl = picUrl;  
    }  
    public String getMediaId() {  
        return MediaId;  
    }  
    public void setMediaId(String mediaId) {  
        MediaId = mediaId;  
    }  
    public String getEventKey() {  
        return EventKey;  
    }  
    public void setEventKey(String eventKey) {  
        EventKey = eventKey;  
    }  
    public String getTicket() {  
        return Ticket;  
    }  
    public void setTicket(String ticket) {  
        Ticket = ticket;  
    }  
    public String getLatitude() {  
        return Latitude;  
    }  
    public void setLatitude(String latitude) {  
        Latitude = latitude;  
    }  
    public String getLongitude() {  
        return Longitude;  
    }  
    public void setLongitude(String longitude) {  
        Longitude = longitude;  
    }  
    public String getPrecision() {  
        return Precision;  
    }  
    public void setPrecision(String precision) {  
        Precision = precision;  
    }  
    public String getEvent() {  
        return Event;  
    }  
    public void setEvent(String event) {  
        Event = event;  
    }  
    public String getMsgId() {  
        return MsgId;  
    }  
    public void setMsgId(String msgId) {  
        MsgId = msgId;  
    }  
    public String getToUserName() {  
        return ToUserName;  
    }  
    public void setToUserName(String toUserName) {  
        ToUserName = toUserName;  
    }  
    public String getFromUserName() {  
        return FromUserName;  
    }  
    public void setFromUserName(String fromUserName) {  
        FromUserName = fromUserName;  
    }  
    public String getCreateTime() {  
        return CreateTime;  
    }  
    public void setCreateTime(String createTime) {  
        CreateTime = createTime;  
    }  
    public String getMsgType() {  
        return MsgType;  
    }  
    public void setMsgType(String msgType) {  
        MsgType = msgType;  
    }

    @Override
    public String toString() {
        return "ReceiveXmlEntity{" +
                "ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", MsgType='" + MsgType + '\'' +
                ", MsgId='" + MsgId + '\'' +
                ", Event='" + Event + '\'' +
                ", EventKey='" + EventKey + '\'' +
                ", Ticket='" + Ticket + '\'' +
                ", Latitude='" + Latitude + '\'' +
                ", Longitude='" + Longitude + '\'' +
                ", Precision='" + Precision + '\'' +
                ", PicUrl='" + PicUrl + '\'' +
                ", MediaId='" + MediaId + '\'' +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Url='" + Url + '\'' +
                ", Location_X='" + Location_X + '\'' +
                ", Location_Y='" + Location_Y + '\'' +
                ", Scale='" + Scale + '\'' +
                ", Label='" + Label + '\'' +
                ", Content='" + Content + '\'' +
                ", Format='" + Format + '\'' +
                ", Recognition='" + Recognition + '\'' +
                ", MenuId='" + MenuId + '\'' +
                ", Encrypt='" + Encrypt + '\'' +
                '}';
    }
}