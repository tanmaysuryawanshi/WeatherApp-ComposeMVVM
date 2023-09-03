package com.tanmaysuryawanshi.weatherforecastmvvm.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.tanmaysuryawanshi.weatherforecastmvvm.R
import com.tanmaysuryawanshi.weatherforecastmvvm.data.DataOrException
import com.tanmaysuryawanshi.weatherforecastmvvm.model.Weather
import com.tanmaysuryawanshi.weatherforecastmvvm.model.WeatherItem
import com.tanmaysuryawanshi.weatherforecastmvvm.navigation.WeatherScreens
import com.tanmaysuryawanshi.weatherforecastmvvm.utils.formatDate
import com.tanmaysuryawanshi.weatherforecastmvvm.utils.formatDay
import com.tanmaysuryawanshi.weatherforecastmvvm.utils.formatDecimals

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    city: String?
) {


ShowData(navController = navController, mainViewModel = mainViewModel, city = city)






    
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowData(navController: NavController, mainViewModel: MainViewModel, city: String?) {

val weatherData= produceState<DataOrException<Weather, Boolean, Exception>>(
    initialValue = DataOrException(loading = true)
){
    value=mainViewModel.getWeatherData(city = city.toString())
}.value

    if(weatherData.loading ==true){
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    }
    else if (weatherData.data !=null){

      //  Text(text = "text"+weatherData.data!!.city.country, modifier = Modifier.fillMaxSize())


    WeatherScreen(navController,weatherData.data!!)


    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(navController: NavController, data: Weather) {


    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0x088757E9))){






            Column(modifier = Modifier
                .fillMaxWidth()
                ) {
                weatherDetails(navController,data)

                Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)) {
Text(text = "This Week",
     modifier = Modifier.padding(16.dp),
    fontSize = 18.sp,
    fontFamily = FontFamily.SansSerif,
    color = colorResource(id = R.color.blacktitle))
                    Text(text = "Next 7 days >",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color(0x80000000))
                }

                LazyRow{
                    items(items=data.list){
                        weatherColumnItem(it)
                    }

                }


            }



        }


}

@Composable
private fun weatherColumnItem(weatherItem: WeatherItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier

            .padding(start = 16.dp)
            .clip(RoundedCornerShape(16.dp))

            .background(Color.White)


    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = formatDecimals(weatherItem.temp.day) ,
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 14.sp,
            fontFamily = FontFamily.SansSerif,
            color = colorResource(id = R.color.blacktitle)
        )
        val imageItemUrl="https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"
        Image(
            painter = rememberAsyncImagePainter(imageItemUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(50.dp)
        )

        Text(
            text = formatDay(weatherItem.dt), fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 8.dp),
            fontFamily = FontFamily.SansSerif,

            color = Color(0x80000000)
        )

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun weatherDetails(
    navController: NavController,
    data: Weather
) {


    val imageUrl="https://openweathermap.org/img/wn/${data.list[0].weather[0].icon}.png"
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

        val (section1, section2) = createRefs()


        Box(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(32.dp))
                .border(width = 1.dp, Color(0xBFffffff))
                .padding(1.dp)
                .background(color = colorResource(id = R.color.purple))

                .constrainAs(section1) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)

                }
        ) {

            Column() {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_grid_view_24),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(18.dp)
                            .size(28.dp)
                            .clickable {
                                // navDrawerState.value = !navDrawerState.value
                            },
                        contentScale = ContentScale.Crop
                    )

                  Text(
                        text = "${data.city.name} | ${data.city.country}"
                      , modifier = Modifier
                          .padding(16.dp)
                          .clickable { navController.navigate(WeatherScreens.SearchScreen.route) },
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = colorResource(id = R.color.blacktitle)

                    )
                    Image(
                        painter = painterResource(id = R.drawable.img),
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(18.dp)
                            .border(2.dp, shape = CircleShape, color = Color.White)
                            .padding(2.dp)
                            .size(32.dp)

                            .clip(CircleShape)
                    )


                }


                Spacer(modifier = Modifier.height(16.dp))

                ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

                    val (box1, box2, box3) = createRefs()


                    Box(

                        modifier = Modifier

                            .clip(RoundedCornerShape(28.dp))
                            .size(200.dp)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        colorResource(id = R.color.boxgstart),
                                        colorResource(id = R.color.boxgend)
                                    )
                                )

                            )

                            .constrainAs(box2) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {

                            Text(
                                text = data.list[0].weather[0].main,
                                modifier = Modifier.padding(top = 8.dp),
                                fontSize = 16.sp,
                                fontFamily = FontFamily.SansSerif,

                                color = Color.White
                            )

                            Text(
                                text = formatDecimals( data.list[0].temp.day)+"\u00B0"
                                , modifier = Modifier
                                    .padding(16.dp),
                                fontSize = 65.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )

                        }
                    }

                    Image(painter = rememberAsyncImagePainter(imageUrl),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(200.dp)

                            .constrainAs(box3) {
                                start.linkTo(box2.start)
                                end.linkTo(box2.end)
                                top.linkTo(box2.bottom)
                                bottom.linkTo(box2.bottom)
                            })


                    Text(
                        text = formatDate( data.list[0].dt), modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .background(color = Color.White)
                            .padding(8.dp)
                            .constrainAs(box1) {
                                start.linkTo(box2.start)
                                end.linkTo(box2.end)
                                top.linkTo(box2.top)
                                bottom.linkTo(box2.top)
                            },
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = colorResource(id = R.color.blacktitle)
                    )


                }

                Spacer(modifier = Modifier.height(72.dp))
            }


        }


        Box(
            modifier = Modifier

                .padding(16.dp)
                .clip(RoundedCornerShape(32.dp))
                .fillMaxWidth(0.8f)
                .shadow(elevation = 100.dp)
                .background(color = Color.White)

                .constrainAs(section2) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(section1.bottom)
                    bottom.linkTo(section1.bottom)
                }

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 16.dp,
                        horizontal = 8.dp
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.wind_icon),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(colorResource(id = R.color.boxgend))
                    )
                    Text(
                        text = data.list[0].speed.toString(),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = colorResource(id = R.color.blacktitle)
                    )
                    Text(
                        text = "Wind", fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light,
                        color = colorResource(id = R.color.blacktitle)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.material_symbols_humidity_low),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(colorResource(id = R.color.boxgend))
                    )
                    Text(
                        text = data.list[0].humidity.toString(),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = colorResource(id = R.color.blacktitle)
                    )
                    Text(
                        text = "Humidity", fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light,
                        color = colorResource(id = R.color.blacktitle)
                    )
                }


                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.mdi_visibility_outline),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(colorResource(id = R.color.boxgend))
                    )
                    Text(
                        text = data.list[0].clouds.toString(),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = colorResource(id = R.color.blacktitle)
                    )
                    Text(
                        text = "Visibility", fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light,
                        color = colorResource(id = R.color.blacktitle)
                    )
                }


            }
        }

    }
}

@Preview
@Composable
fun appPreview() {
   // WeatherScreen(weatherData)
}
