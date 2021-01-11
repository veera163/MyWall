package com.frazen.edaftar.api;
import com.frazen.edaftar.Model.AppAccessInfo;
import com.frazen.edaftar.Model.DashBoardData;
import com.frazen.edaftar.Model.EndorsementDetails;
import com.frazen.edaftar.Model.Grievance;
import com.frazen.edaftar.Model.InitEndorsement;
import com.frazen.edaftar.Model.InitMeetingDetails;
import com.frazen.edaftar.Model.LettersAndNotes;
import com.frazen.edaftar.Model.Mandals;
import com.frazen.edaftar.Model.MeetingInfo;
import com.frazen.edaftar.Model.MinisterData;
import com.frazen.edaftar.Model.Peshi;
import com.frazen.edaftar.Model.ReimbrusementAndLoc;
import com.frazen.edaftar.Model.SelectDayMeeting;
import com.frazen.edaftar.Model.UpdateEndorse;
import com.frazen.edaftar.Model.UserDomain;
import com.frazen.edaftar.Model.ViewEndorsementDetails;
import com.frazen.edaftar.Model.ViewGrievance;
import com.frazen.edaftar.Model.ViewLetterAndNotes;
import com.frazen.edaftar.Model.ViewPeshi;
import com.frazen.edaftar.Model.ViewReimbrusementAndLoc;
import com.frazen.edaftar.Model.Villages;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * Created by Ramakanth on 20-12-2019.
 */

public interface APIServiceCalls {

    @FormUrlEncoded
    @POST("oauth/token")
    Call<AppAccessInfo> userLogin(@Field("grant_type") String grantType,
                                  @Field("username") String username,
                                  @Field("password") String password);
    @GET
    Call<UserDomain> getUser(@Url String url);


    @GET
    Call<List<Mandals>> getMandel(@Url String url);
    @GET
    Call<List<Villages>> getVillage(@Url String url);


    @GET
    Call<List<EndorsementDetails>> getAllEndorse(@Url String url);
    @GET
    Call<InitEndorsement> getInitEndorse(@Url String url);
    @GET
    Call<ViewEndorsementDetails> getEndorse(@Url String url);
    @POST("endorsement/update")
    Call<Boolean> updateEndorse(@Body UpdateEndorse updateEndorse);
    @Multipart
    @POST("endorsement/create")
    Call<Boolean> createEndorsement(@Part("serialNo") RequestBody  serialNo,
                                    @Part("endorsementNo") RequestBody  endorsementNo,
                                    @Part("status") RequestBody  status,
                                    @Part("petitionerCategory") RequestBody  petitionerCategory,
                                    @Part("petitionerName") RequestBody  petitionerName,
                                    @Part("petitionerMobile") RequestBody  petitionerMobile,
                                    @Part("createDate") RequestBody  createDate,
                                    @Part("referredBy") RequestBody  referredBy,
                                    @Part("petitionDetails") RequestBody  petitionDetails,
                                    @Part("endorsedOfficer") RequestBody  endorsedOfficer,
                                    @Part("department") RequestBody  department,
                                    @Part("address") RequestBody  address,
                                    @Part("hamlet") RequestBody  hamlet,
                                    @Part("village") RequestBody  village,
                                    @Part("mandal") RequestBody  mandal,
                                    @Part("district") RequestBody  district,
                                    @Part("replyReceived") RequestBody  replyReceived,
                                    @Part("refByPhone") RequestBody  refByPhone,
                                    @Part("username") RequestBody  username,
                                    @Part("actTime") RequestBody  actTime,
                                    @Part("remarks") RequestBody  remarks,
                                    @Part MultipartBody.Part doc);



    @GET
    Call<List<SelectDayMeeting>> getDayMeetings(@Url String url);
    @GET
    Call<InitMeetingDetails> getInitMeeting(@Url String url);
    @POST("meeting/create")
    Call<Boolean> createMeeting(@Body MeetingInfo meetingInfo);
    @POST("meeting/update")
    Call<Boolean> updateMeeting(@Body MeetingInfo meetingInfo);
    @GET
    Call<List<MinisterData>> getMinister(@Url String url);


    @GET
    Call<DashBoardData> getDashBoard(@Url String url);

    @GET
    Call<List<Grievance>> getGrievance(@Url String url);
    @GET
    Call<ViewGrievance> getGrievanceDetails(@Url String url);

    @GET
    Call<List<ReimbrusementAndLoc>> getReimbrusementAndLoc(@Url String url);
    @GET
    Call<ViewReimbrusementAndLoc> getReimbrusementAndLocDetails(@Url String url);


    @GET
    Call<List<Peshi>> getPeshi(@Url String url);
    @GET
    Call<ViewPeshi> getPeshiDetails(@Url String url);

    @GET
    Call<List<LettersAndNotes>> getLetters(@Url String url);
    @GET
    Call<ViewLetterAndNotes> getLettersDetails(@Url String url);

}

