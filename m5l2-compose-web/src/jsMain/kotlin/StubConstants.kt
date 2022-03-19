object StubConstants {
    val names = listOf(
        "Boris",
        "Barsik",
        "Marusya",
        "Kesha",
        "Semen",
        "Feofan",
        "Basilio",
        "Alisa"
    )
    val images = listOf(
        "images/cat1.png",
        "https://img.joinfo.com/i/2018/06/800x0/5b30ce1e882dc.jpg",
        "https://funik.ru/wp-content/uploads/2018/10/271015_6.jpg",
        "https://images.wallpaperscraft.ru/image/single/kot_morda_ispug_polosatyy_vzglyad_53497_3840x2400.jpg",
        "https://w-dog.ru/wallpapers/6/1/529936426991201/kot-krasavec-poza.jpg",
        "https://lookw.ru/8/828/1476173824-cats-wallpapers-3-32.jpg",
        "https://kuda-spb.ru/uploads/7722cf01bab5c04f550d341c5879369e.jpg",
        "https://lookw.ru/8/828/1476173846-cats-wallpapers-3-73.jpg"
    )

    val cats = names.mapIndexed { index, name ->
        name to images[index]
    }.toMap()
}
